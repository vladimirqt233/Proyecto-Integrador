import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { abcForms } from '../../../../../../../environments/generals';
import { Cliente } from '../../models/cliente';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';

@Component({
    selector: 'app-clientes-list',
    imports: [
        CommonModule,
        RouterOutlet,
        MatButtonModule,
        MatIconModule
    ],
    standalone: true,
    template: `
        <div class="w-full mx-auto p-6 bg-white rounded overflow-hidden shadow-lg">
            <!-- Encabezado principal -->
            <div class="flex justify-between items-center mb-2 bg-slate-300 text-black p-4 rounded">
                <h2 class="text-2xl font-bold">
                    Lista de <span class="text-primary">Clientes</span>
                </h2>
                <button mat-flat-button [color]="'black'" (click)="goNew()">
                    <mat-icon [svgIcon]="'heroicons_outline:plus'"></mat-icon>
                    <span class="ml-2">Nuevo Cliente</span>
                </button>
            </div>

            <!-- Tabla de clientes -->
            <div class="bg-white rounded overflow-hidden shadow-lg">
                <div class="p-2 overflow-scroll px-0">
                    <table class="w-full table-fixed">
                        <thead class="bg-primary-600 text-white">
                        <tr>
                            <th class="w-1/6 table-head text-center px-5 border-r">#</th>
                            <th class="w-2/6 table-header text-center px-5 border-r">DNI</th>
                            <th class="w-1/6 table-header text-center border-r">Nombre</th>
                            <th class="w-2/6 table-header text-center">Apellidos</th>
                            <th class="w-2/6 table-header text-center px-5 border-r">Teléfono</th>
                            <th class="w-1/6 table-header text-center border-r">Correo Electrónico</th>
                            <th class="w-2/6 table-header text-center">Dirección</th>
                        </tr>
                        </thead>
                        <tbody *ngFor="let cliente of clientes; let i = index" class="bg-white">
                        <tr class="hover:bg-gray-100">
                            <td class="w-1/6 p-2 text-center border-b">{{ i }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ cliente.dni }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ cliente.nombre }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ cliente.apellidos }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ cliente.telefono }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ cliente.correoElectronico }}</td>
                            <td class="w-2/6 p-2 text-start border-b text-sm">{{ cliente.direccion }}</td>
                            <td class="w-2/6 p-2 text-center border-b text-sm">
                                <div class="flex justify-center space-x-3">
                                    <mat-icon
                                        class="text-amber-400 hover:text-amber-500 cursor-pointer"
                                        (click)="goEdit(cliente.id)">
                                        edit
                                    </mat-icon>
                                    <mat-icon
                                        class="text-rose-500 hover:text-rose-600 cursor-pointer"
                                        (click)="goDelete(cliente.id)">
                                        delete_sweep
                                    </mat-icon>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    `
})
export class ClientListComponent implements OnInit {
    abcForms: any;
    @Input() clientes: Cliente[] = [];
    @Output() eventNew = new EventEmitter<boolean>();
    @Output() eventEdit = new EventEmitter<number>();
    @Output() eventDelete = new EventEmitter<number>();

    constructor(private _matDialog: MatDialog) {}

    ngOnInit(): void {
        this.abcForms = abcForms;
    }

    public goNew(): void {
        this.eventNew.emit(true);
    }

    public goEdit(id: number): void {
        this.eventEdit.emit(id);
    }

    public goDelete(id: number): void {
        this.eventDelete.emit(id);
    }
}
