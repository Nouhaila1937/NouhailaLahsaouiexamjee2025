import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
 import { Observable } from 'rxjs';
import { RemboursementDTO } from '../model/RemboursementDTO.model';
@Injectable({
  providedIn: 'root'
}) 
export class RemboursementService {
  private apiUrl = 'http://localhost:8080/remboursements';

  constructor(private http: HttpClient) {}

  getAll(): Observable<RemboursementDTO[]> {
    return this.http.get<RemboursementDTO[]>(this.apiUrl);
  }

  getById(id: number): Observable<RemboursementDTO> {
    return this.http.get<RemboursementDTO>(`${this.apiUrl}/${id}`);
  }

  create(remboursement: RemboursementDTO, creditId: number): Observable<RemboursementDTO> {
    return this.http.post<RemboursementDTO>(`${this.apiUrl}/credit/${creditId}`, remboursement);
  }

  update(id: number, remboursement: RemboursementDTO): Observable<RemboursementDTO> {
    return this.http.put<RemboursementDTO>(`${this.apiUrl}/${id}`, remboursement);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
 