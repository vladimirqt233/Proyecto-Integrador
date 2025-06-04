import { Vehiculo } from '../models/vehiculo';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { VehiculoNewComponent } from '../components/form/vehiculo-new.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { VehiculoEditComponent } from '../components/form/vehiculos-edit.component';
import {ConfirmDialogService} from "../../../../../shared/confirm-dialog/confirm-dialog.service";
import {ClientListComponent} from "../components";
import {VehiculoService} from "../../../../../providers/services/setup/vehiculo.service";


@Component({
    selector: 'app-Vehiculoss-container',
    standalone: true,
    imports: [
        CommonModule,
        RouterOutlet,
        ClientListComponent,
        VehiculoNewComponent,
        VehiculoEditComponent,
        FormsModule,
        ReactiveFormsModule,
    ],
    template: `
        <app-vehiculo-list
            class="w-full"
            [vehiculos]="vehiculos"
            (eventNew) = "eventNew($event)"
            (eventEdit)="eventEdit($event)"
            (eventDelete)="eventDelete($event)"
        ></app-vehiculo-list>
    `,
})
export class VehiculoContainerComponent implements OnInit {
    public error: string = '';
    public vehiculos: Vehiculo[] = [];
    public vehiculo = new Vehiculo();

    constructor(
        private _vehiculoService: VehiculoService,
        private _confirmDialogService:ConfirmDialogService,
        private _matDialog: MatDialog,
    ) {}

    ngOnInit() {
        this.getVehiculoss();
    }

    getVehiculoss(): void {
        this._vehiculoService.getAll$().subscribe(
            (response) => {
                this.vehiculos = response;
            },
            (error) => {
                this.error = error;
            }
        );
    }

    public eventNew($event: boolean): void {
        if ($event) {
            const VehiculoForm = this._matDialog.open(VehiculoNewComponent);
            VehiculoForm.componentInstance.title = 'Nuevo Vehiculo' || null;
            VehiculoForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.saveVehiculo(result);
                }
            });
        }
    }

    saveVehiculo(data: Object): void {
        this._vehiculoService.add$(data).subscribe((response) => {
            if (response) {
                this.getVehiculoss()
            }
        });
    }

    eventEdit(idCategory: number): void {
        const listById = this._vehiculoService
            .getById$(idCategory)
            .subscribe(async (response) => {
                this.vehiculo = (response) || {};
                this.openModalEdit(this.vehiculo);
                listById.unsubscribe();
            });
    }

    openModalEdit(data: Vehiculo) {
        if (data) {
            const VehiculoForm = this._matDialog.open(VehiculoEditComponent);
            VehiculoForm.componentInstance.title =`Editar <b>${data.marca||data.id} </b>`;
            VehiculoForm.componentInstance.vehiculo = data;
            VehiculoForm.afterClosed().subscribe((result: any) => {
                if (result) {
                    this.editVehiculo(data.id,result);
                }
            });
        }
    }

    editVehiculo( idVehiculo: number,data: Object) {
        this._vehiculoService.update$(idVehiculo,data).subscribe((response) => {
            if (response) {
                this.getVehiculoss()
            }
        });
    }


    public eventDelete(idVehiculo: number) {
        this._confirmDialogService.confirmDelete(
            {
                // title: 'Confirmación Personalizada',
                // message: `¿Quieres proceder con esta acción ${}?`,
            }
        ).then(() => {
            this._vehiculoService.delete$(idVehiculo).subscribe((response) => {
                this.vehiculos = response;
            });
            this.getVehiculoss();
        }).catch(() => {
        });

    }
}
