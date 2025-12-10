export interface Contract {
  id: number;
  signedById: string;       // UUID → string nel FE
  title: string;
  description?: string;    // opzionale
  startDate: string;       // ISO string
  endDate: string;
  createdAt: string;
}

export interface ContractCreate {
  signedById: string;
  title: string;
  description?: string;
  startDate: string;
  endDate: string;
}

export interface ContractUpdate {
  title?: string;
  description?: string;
  startDate?: string;
  endDate?: string;
}
