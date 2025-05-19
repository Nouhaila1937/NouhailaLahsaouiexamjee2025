export interface Credit {
  id: number;
  dateDemande: string;
  statut: string;
  dateAcception?: string;
  montant: number;
  dureeRemboursement: number;
  tauxInteret: number;
  type: string; // personnel, immobilier, professionnel
  motif?: string; // personnel et professionnel
  typeBien?: string; // immobilier
  raisonSociale?: string; // professionnel
  clientId: number;
}