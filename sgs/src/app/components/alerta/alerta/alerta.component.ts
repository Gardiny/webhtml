import { Component, Inject, PLATFORM_ID } from '@angular/core';
import { AlertaService } from '../../../service/alerta.service';
import { Alerta } from '../../../model/alerta';
import { NavigationStart, Router } from '@angular/router';
import { ETipoAlerta } from '../../../model/e-tipo-alerta';
import { isPlatformBrowser } from '@angular/common';

@Component({
  selector: 'app-alerta',
  standalone: true,
  imports: [],
  templateUrl: './alerta.component.html',
  styleUrls: ['./alerta.component.scss'] // Corrigido o nome da propriedade: styleUrl -> styleUrls
})
export class AlertaComponent {

  constructor(
    private servico: AlertaService,
    private router: Router,
    @Inject(PLATFORM_ID) private platformId: Object // Injeta o PLATFORM_ID para verificação do ambiente
  ) {}

  ngOnInit(): void {
    this.servico.receberAlerta().subscribe({
      next: (alerta: Alerta) => {
        this.exibirAlerta(alerta);
      }
    });

    this.router.events.subscribe({
      next: (evento) => {
        if (evento instanceof NavigationStart) {
          this.fecharAlerta();
        }
      }
    });
  }

  exibirAlerta(alerta: Alerta): void {
    if (isPlatformBrowser(this.platformId)) {
      // Apenas manipula o DOM se estiver no navegador
      const elAlerta = document.querySelector<HTMLElement>('div.alerta');
      const elMensagem = document.querySelector<HTMLElement>('div.alerta span#mensagem');
      if (elMensagem && elAlerta) {
        elMensagem.innerText = alerta.mensagem;
        elAlerta.classList.add(alerta.tipo);
        elAlerta.classList.remove('inativo');
      }
      setTimeout(() => {
        this.fecharAlerta();
      }, 5000);
    }
  }

  fecharAlerta(): void {
    if (isPlatformBrowser(this.platformId)) {
      // Apenas manipula o DOM se estiver no navegador
      const elAlerta = document.querySelector<HTMLElement>('div.alerta');
      if (elAlerta) {
        elAlerta.classList.add('inativo');
        elAlerta.classList.remove(
          ETipoAlerta.SUCESSO,
          ETipoAlerta.ERRO
        );
      }
    }
  }
}


// import { Component } from '@angular/core';
// import { AlertaService } from '../../../service/alerta.service';
// import { Alerta } from '../../../model/alerta';
// import { NavigationStart, Router } from '@angular/router';
// import { ETipoAlerta } from '../../../model/e-tipo-alerta';

// @Component({
//   selector: 'app-alerta',
//   standalone: true,
//   imports: [],
//   templateUrl: './alerta.component.html',
//   styleUrl: './alerta.component.scss'
// })
// export class AlertaComponent {

//   constructor(private servico: AlertaService, private router: Router) {}

//   ngOnInit(): void {

//     this.servico.receberAlerta().subscribe({
//       next: (alerta: Alerta) => {
//         this.exibirAlerta(alerta);
//       }
//     })

//     this.router.events.subscribe({
//       next: (evento) => {
//         if (evento instanceof NavigationStart) {
//           this.fecharAlerta();
//         }
//       }
//     })
//   }

//   exibirAlerta(alerta: Alerta): void {
//     const elAlerta = document.querySelector<HTMLElement>('div.alerta');
//     const elMensagem = document.querySelector<HTMLElement>('div.alerta span#mensagem');
//     if (elMensagem && elAlerta) {
//       elMensagem.innerText = alerta.mensagem;
//       elAlerta.classList.add(alerta.tipo);
//       elAlerta.classList.remove('inativo');
//     }
//     setTimeout(() => {
//       this.fecharAlerta();
//     }, 5000);
//   }

//   fecharAlerta(): void {
//     const elAlerta = document.querySelector<HTMLElement>('div.alerta');
//     if (elAlerta) {
//       elAlerta.classList.add('inativo');
//       elAlerta.classList.remove(
//         ETipoAlerta.SUCESSO,
//         ETipoAlerta.ERRO
//       )
//     }
//   }

// }
