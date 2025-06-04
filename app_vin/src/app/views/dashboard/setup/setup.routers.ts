import {Routes} from '@angular/router';
import {SetupComponent} from "./setup.component";

export default [
    {
        path: '',
        component: SetupComponent,
        children: [
            {path: 'registroenvio', loadChildren: () => import('./registroenvio/registroenvio.routers')},
            {path: 'vehiculo', loadChildren: () => import('./vehiculo/vehiculo.routers')},
            {path: 'client', loadChildren: () => import('./client/cliente.routers')},
            {path: 'category', loadChildren: () => import('./category/category.routers')},
            {path: 'role', loadChildren: () => import('./roles/roles.routers')},
            {path: 'users', loadChildren: () => import('./user/users-routers')},
            {path: 'user', loadChildren: () => import('./user/users-routers')},
            {path: 'tree', loadChildren: () => import('./tree/tree.routers')},
            {path: 'access', loadChildren: () => import('./access/access.routers')},
        ],
    },
] as Routes;
