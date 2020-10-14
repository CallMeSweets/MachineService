import {Location} from './Location';


export interface Machine {
  id: number;
  ownerId: number;
  locationDTO: Location;
  name: string;
  totalSpace: number;
  machineType: string;
  description: string;
}
