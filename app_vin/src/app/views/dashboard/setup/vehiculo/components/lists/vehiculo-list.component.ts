import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

import { abcForms } from '../../../../../../../environments/generals';
import { Vehiculo } from '../../models/vehiculo';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog } from '@angular/material/dialog';

@Component({
    selector: 'app-vehiculo-list',
    imports: [CommonModule, RouterOutlet, MatButtonModule, MatIconModule],
    standalone: true,
    template: `
        <div class="w-full mx-auto p-6 bg-white rounded overflow-hidden shadow-lg">
            <!-- Encabezado principal -->
            <div class="flex justify-between items-center mb-2 bg-slate-300 text-black p-4 rounded">
                <h2 class="text-2xl font-bold">
                    Lista de <span class="text-primary">Vehiculos</span>
                </h2>
                <button mat-flat-button [color]="'primary'" (click)="goNew()">
                    <mat-icon [svgIcon]="'heroicons_outline:plus'"></mat-icon>
                    <span class="ml-2">Nueva vehiculo</span>
                </button>
            </div>
            <div class="bg-white rounded overflow-hidden shadow-lg">
                <div class="p-2 overflow-scroll px-0">
                    <table class="w-full table-fixed">
                        <thead class="bg-primary-600 text-white">
                        <tr>
                            <th class="w-1/6 table-head text-center px-5 border-r">#</th>
                            <th class="w-2/6 table-header text-center px-5 border-r">
                                marca
                            </th>
                            <th class="w-2/6 table-header text-center px-5 border-r">
                                modelo
                            </th>
                            <th class="w-2/6 table-header text-center px-5 border-r">
                                placa
                            </th>
                            <th class="w-2/6 table-header text-center">
                                color
                            </th>
                            <th class="w-2/6 table-header text-center">
                                tipo
                            </th>
                        </tr>
                        </thead>
                        <tbody
                            class="bg-white"
                            *ngFor="let r of vehiculos; let i = index">
                        <tr class="hover:bg-gray-100">
                            <td class="w-1/6 p-2 text-center border-b">
                                {{ i }}
                            </td>
                            <td class="w-2/6 p-2  text-start border-b text-sm">
                                {{ r.marca }}
                            </td>
                            <td class="w-2/6 p-2  text-start border-b text-sm">
                                {{ r.modelo }}
                            </td>
                            <td class="w-2/6 p-2  text-start border-b text-sm">
                                {{ r.placa }}
                            </td>
                            <td class="w-2/6 p-2  text-start border-b text-sm">
                                {{ r.color }}
                            </td>
                            <td class="w-2/6 p-2  text-start border-b text-sm">
                                {{ r.tipo }}
                            </td>

                            <td class="w-2/6 p-2 text-center border-b text-sm">
                                <div class="flex justify-center space-x-3">
                                    <mat-icon class="text-amber-400 hover:text-amber-500 cursor-pointer"
                                              (click)="goEdit(r.id)">edit
                                    </mat-icon>

                                    <mat-icon class="text-rose-500 hover:text-rose-600 cursor-pointer"
                                              (click)="goDelete(r.id)">delete_sweep
                                    </mat-icon>
                                    <!-- <mat-icon
                                         class="text-sky-400 hover:text-sky-600 cursor-pointer"
                                         (click)="goAssign(r.id)"
                                         >swap_horiz
                                     </mat-icon>-->
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <!--<div class="px-5 py-2 bg-white border-t flex flex-col xs:flex-row items-center xs:justify-between">
                        <span class="text-xs xs:text-sm text-gray-900">
                            Showing 1 to 4 of 50 Entries
                        </span>
                        <div class="inline-flex mt-2 xs:mt-0">
                            <button class="text-sm text-primary-50 transition duration-150 hover:bg-primary-500 bg-primary-600 font-semibold py-2 px-4 rounded-l">
                                Prev
                            </button>
                            &nbsp; &nbsp;
                            <button class="text-sm text-primary-50 transition duration-150 hover:bg-primary-500 bg-primary-600 font-semibold py-2 px-4 rounded-r">
                                Next
                            </button>
                        </div>
                    </div>-->
                </div>
            </div>
        </div>
    `,
})
export class ClientListComponent implements OnInit {
    abcForms: any;
    @Input() vehiculos: Vehiculo[] = [];
    @Output() eventNew = new EventEmitter<boolean>();
    @Output() eventEdit = new EventEmitter<number>();
    @Output() eventDelete = new EventEmitter<number>();
    @Output() eventAssign = new EventEmitter<number>();

    constructor(private _matDialog: MatDialog) {}

    ngOnInit() {
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
