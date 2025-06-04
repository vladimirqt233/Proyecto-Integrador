import { Routes } from '@angular/router';
import {RegistroenvioContainerComponent} from "./containers/registroenvio-container.component";
import {RegistroenvioComponent} from "./registroenvio.component";

export default [

    {
        path     : '',
        component: RegistroenvioComponent,
        children: [
            {
                path: '',
                component: RegistroenvioContainerComponent,
                data: {
                    title: 'Registroenvio'
                }
            },
        ],
    },
] as Routes;
