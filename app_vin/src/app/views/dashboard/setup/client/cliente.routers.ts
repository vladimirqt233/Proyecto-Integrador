import { Routes } from '@angular/router';
import {ClienteContainerComponent} from "./containers/cliente-container.component";
import {ClienteComponent} from "./cliente.component";

export default [

  {
    path     : '',
    component: ClienteComponent,
    children: [
      {
        path: '',
        component: ClienteContainerComponent,
        data: {
          title: 'Clientes'
        }
      },
    ],
  },
] as Routes;
