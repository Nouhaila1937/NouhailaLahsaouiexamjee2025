import { Component, OnInit } from '@angular/core';
import { RemboursementDTO } from '../../model/RemboursementDTO.model';
import { RemboursementService } from '../../services/remboursement.service';

@Component({
  selector: 'app-remboursement',
  imports: [],
  templateUrl: './remboursement.component.html',
  styleUrl: './remboursement.component.scss'
})
export class RemboursementsComponent implements OnInit {
  remboursements: RemboursementDTO[] = [];

  constructor(private remboursementService: RemboursementService) {}

  ngOnInit(): void {
    this.loadRemboursements();
  }

  loadRemboursements(): void {
    this.remboursementService.getAll().subscribe(data => {
      this.remboursements = data;
    });
  }
}