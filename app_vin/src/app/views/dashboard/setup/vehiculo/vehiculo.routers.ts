import { Routes } from '@angular/router';
import {VehiculoContainerComponent} from "./containers/vehiculo-container.component";
import {VehiculoComponent} from "./vehiculo.component";

export default [

    {
        path     : '',
        component: VehiculoComponent,
        children: [
            {
                path: '',
                component: VehiculoContainerComponent,
                data: {
                    title: 'Vehiculos'
                }
            },
        ],
    },
] as Routes;
