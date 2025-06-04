import { RegistroEnvio } from '../models/registroenvio';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RegistroenvioNewComponent } from '../components/form/registroenvio-new.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RegistroenvioEditComponent } from '../components/form/registroenvio-edit.component';
import {ConfirmDialogService} from "../../../../../shared/confirm-dialog/confirm-dialog.service";
import {RegistroenvioListComponent} from "../components";
import {RegistroenvioService} from "../../../../../providers/services/setup/registroenvio.service";

@Component({
    selector: 'app-registroenvios-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        RegistroenvioEditComponent,
        RegistroenvioNewComponent,
        RegistroenvioListComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-registroenvio-list
            class="w-full"
            [registroenvios]="registroenvios"
            (eventNew)="eventNew($event)"
            (eventEdit)="eventEdit($event)"
            (eventDelete)="eventDelete($event)"
        ></app-registroenvio-list>
    `,
})
export class RegistroenvioContainerComponent implements OnInit {
    public error: string = '';
    public registroenvios: RegistroEnvio[] = [];
    public registroenvio = new RegistroEnvio();

    constructor(
        private _registroenvioService: RegistroenvioService,
        private _confirmDialogService:ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit() {
        this.getRegistroenvioss();
    }

    getRegistroenvioss(): void {
        this._registroenvioService.getAll$().subscribe(
            (response) => {
                this.registroenvios = response;
            },
            (error) => {
                this.error = error;
            }
        );
    }

    public eventNew($event: boolean): void {
        if ($event) {
            const registroenvioForm = this._matDialog.open(RegistroenvioNewComponent);
            registroenvioForm.componentInstance.title = 'Nuevo Registro' || null;
            registroenvioForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.saveClient(result);
                }
            });
        }
    }

    saveClient(data: Object): void {
        this._registroenvioService.add$(data).subscribe((response) => {
            if (response) {
                this.getRegistroenvioss()
            }
        });
    }

    eventEdit(idClient: number): void {
        const listById = this._registroenvioService
            .getById$(idClient)
            .subscribe(async (response) => {
                this.registroenvio = (response) || {};
                this.openModalEdit(this.registroenvio);
                listById.unsubscribe();
            });
    }

    openModalEdit(data: RegistroEnvio) {
        console.log(data);
        if (data) {
            const registroenvioForm = this._matDialog.open(RegistroenvioEditComponent);
            registroenvioForm.componentInstance.title =`Editar <b>${data.titulo||data.id} </b>`;
            registroenvioForm.componentInstance.registroenvio = data;
            registroenvioForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.editRegistroenvios( data.id,result);
                }
            });
        }
    }

    editRegistroenvios( idRegistroenvio: number,data: Object) {
        this._registroenvioService.update$(idRegistroenvio,data).subscribe((response) => {
            if (response) {
                this.getRegistroenvioss()
            }
        });
    }


    public eventDelete(idRegistroenvio: number) {
        this._confirmDialogService.confirmDelete(
            {
                // title: 'Confirmación Personalizada',
                // message: `¿Quieres proceder con esta acción ${}?`,
            }
        ).then(() => {
            this._registroenvioService.delete$(idRegistroenvio).subscribe((response) => {
                this.registroenvios = response;
            });
            this.getRegistroenvioss();
        }).catch(() => {
        });

    }
}
