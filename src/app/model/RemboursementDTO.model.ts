export interface RemboursementDTO {
  id?: number;
  montant: number;
  dateRemboursement: string; // Format ISO, ex: '2025-05-19'
  type: 'PARTIEL' | 'TOTAL'; // Remplacez par vos types réels
  creditId: number;
}
