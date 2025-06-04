import { Cliente } from '../models/cliente';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ClienteNewComponent } from '../components/form/cliente-new.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ClienteEditComponent } from '../components/form/cliente-edit.component';
import {ConfirmDialogService} from "../../../../../shared/confirm-dialog/confirm-dialog.service";
import {ClientListComponent} from "../components";
import {ClienteService} from "../../../../../providers/services/setup/cliente.service";

@Component({
    selector: 'app-clientes-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        ClientListComponent,
        ClienteNewComponent,
        ClienteEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-clientes-list
            class="w-full"
            [clientes]="clientes"
            (eventNew)="eventNew($event)"
            (eventEdit)="eventEdit($event)"
            (eventDelete)="eventDelete($event)"
        ></app-clientes-list>
    `,
})
export class ClienteContainerComponent implements OnInit {
    public error: string = '';
    public clientes: Cliente[] = [];
    public cliente = new Cliente();

    constructor(
        private _clienteService: ClienteService,
        private _confirmDialogService:ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit() {
        this.getClients();
    }

    getClients(): void {
        this._clienteService.getAll$().subscribe(
            (response) => {
                this.clientes = response;
            },
            (error) => {
                this.error = error;
            }
        );
    }

    public eventNew($event: boolean): void {
        if ($event) {
            const clienteForm = this._matDialog.open(ClienteNewComponent);
            clienteForm.componentInstance.title = 'Nuevo Cliente' || null;
            clienteForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.saveClient(result);
                }
            });
        }
    }

    saveClient(data: Object): void {
        this._clienteService.add$(data).subscribe((response) => {
        if (response) {
            this.getClients()
        }
        });
    }

    eventEdit(idClient: number): void {
        const listById = this._clienteService
            .getById$(idClient)
            .subscribe(async (response) => {
                this.cliente = (response) || {};
                this.openModalEdit(this.cliente);
                listById.unsubscribe();
            });
    }

    openModalEdit(data: Cliente) {
        console.log(data);
        if (data) {
            const clienteForm = this._matDialog.open(ClienteEditComponent);
            clienteForm.componentInstance.title =`Editar <b>${data.nombre||data.id} </b>`;
            clienteForm.componentInstance.cliente = data;
            clienteForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.editClient( data.id,result);
                }
            });
        }
    }

    editClient( idClient: number,data: Object) {
        this._clienteService.update$(idClient,data).subscribe((response) => {
            if (response) {
                this.getClients()
            }
        });
    }


    public eventDelete(idClient: number) {
        this._confirmDialogService.confirmDelete(
            {
                // title: 'Confirmación Personalizada',
                // message: `¿Quieres proceder con esta acción ${}?`,
            }
        ).then(() => {
            this._clienteService.delete$(idClient).subscribe((response) => {
                this.clientes = response;
            });
            this.getClients();
        }).catch(() => {
        });

    }
}
