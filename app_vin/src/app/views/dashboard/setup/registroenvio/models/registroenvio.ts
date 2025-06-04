import { Vehiculo } from '../../vehiculo/models/vehiculo';
import { Cliente } from '../../client/models/cliente';

export class RegistroEnvio {
    id!: number;
    titulo: string;
    descripcion: string;
    fecha: string; // Fecha en formato ISO
    vehiculoId: number; // Llave foránea para Vehículo
    clienteId: number; // Llave foránea para Cliente
    estadoEnvio: EstadoEnvio; // Enum para el estado del envío
    vehiculoDto?: Vehiculo; // Detalle del vehículo relacionado
    clienteDto?: Cliente; // Detalle del cliente relacionado
}

// Enumeración para el estado del envío
export enum EstadoEnvio {
    PENDIENTE = 'PENDIENTE',
    EN_CAMINO = 'EN_CAMINO',
    ENTREGADO = 'ENTREGADO',
}
