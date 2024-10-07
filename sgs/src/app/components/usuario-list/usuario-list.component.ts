import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { UsuarioService } from '../../service/usuario.service';
import { Usuario } from '../../model/usuario';
import { RespostaPaginada } from '../../model/resposta-paginada';
import { RequisicaoPaginada } from '../../model/requisicao-paginada';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { isPlatformBrowser } from '@angular/common';
import { IList } from '../i-list';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-usuario-list',
  standalone: true,
  templateUrl: './usuario-list.component.html',
  styleUrls: ['./usuario-list.component.css'],
  imports: [CommonModule, FormsModule, RouterLink]
})
export class UsuarioListComponent implements IList<Usuario>, OnInit {
  registros: Usuario[] = [];
  respostaPaginada: RespostaPaginada<Usuario> = {} as RespostaPaginada<Usuario>;
  requisicaoPaginada: RequisicaoPaginada = new RequisicaoPaginada();
  termoBusca: string | undefined = '';
  constructor(private servico: UsuarioService, @Inject(PLATFORM_ID) private platformId: Object) { }
  ngOnInit(): void {
    if (isPlatformBrowser(this.platformId)) {
      this.requisicaoPaginada.size = parseInt(localStorage.getItem('tamanhoPagina') || '5');
    } else {
      this.requisicaoPaginada.size = 5;
    }
    this.get();
  }
  get(termoBusca?: string): void {
    this.termoBusca = termoBusca;
    this.servico.get(termoBusca, this.requisicaoPaginada).subscribe({
      next: (resposta: RespostaPaginada<Usuario>) => {
        this.registros = resposta.content;
        this.respostaPaginada = resposta;
      }
    });
  }
  delete(id: number): void {
    this.servico.delete(id).subscribe({
      next: () => {
        this.registros = this.registros.filter(item => item.id !== id);
      },
      error: (err) => {
        console.error('Erro ao excluir a Usuario:', err);
      }
    });
  }
}

