import { Component, Inject } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Usuario } from '../../../model/usuario';
import { ILoginService, LoginService } from '../../../service/login/i-login.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']  // Corrigido para styleUrls
})
export class LoginComponent {
  usuario: Usuario = <Usuario>{};

  constructor(@Inject(LoginService) private servico: ILoginService) {}

  submit(form: NgForm): void {
    this.servico.login(this.usuario);  // Chama o serviço para realizar o login
    form.resetForm();  // Reseta o formulário
  }
}
