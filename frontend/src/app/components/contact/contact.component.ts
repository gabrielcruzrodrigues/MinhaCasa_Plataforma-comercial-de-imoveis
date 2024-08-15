import { ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { MessageService } from '../../services/message.service';
import { HttpResponse } from '@angular/common/http';
import { ModalTextComponent } from '../layout/modal-text/modal-text.component';
import { FooterComponent } from "../layout/footer/footer.component";

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [
    FormsModule, FormsModule, ReactiveFormsModule, ModalTextComponent, FooterComponent, FooterComponent
  ],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent implements OnInit{

  formData = new FormData();
  form: FormGroup;
  showModalText: boolean = false;
  message: string = '';
  showModal: boolean = false;
  @ViewChild(ModalTextComponent) modalComponent!: ModalTextComponent;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private messageService: MessageService,
    private cdr: ChangeDetectorRef,
  ){
    this.form = this.fb.group({
      text: ['', Validators.required],
      type: ['', Validators.required],
      senderId: ['', Validators.required]
    })
  }

  ngOnInit(): void {
    if (!this.authService.verifyIfAreLoggedIn()) {
      this.router.navigate(['/login']);
    } else {
      const id = this.authService.getUserId();
      this.form.patchValue({
        senderId: id
      })
    }
  }

  selectedOption: string = '';

  selectOption(option: string) {
    this.form.patchValue({
      type: option
    })
    this.selectedOption = option;
  }

  submit(): void {
    if (!this.verifyFieldsBeforeSubmit()) {
      return;
    }
    
    this.messageService.createMessage(this.form.value).subscribe({
      next: (response: HttpResponse<any>) => {
        if (response.status === 201) {
          this.activeModalText('Mensagem enviada com sucesso!');
          this.clearTextarea();
        }
      },
      error: (error) => {
        this.activeModalText('Ocorreu um erro interno, por favor tente mais tarde!');
        console.log(error);
        this.waitForModalClose().then(() => {
          this.router.navigate(["/"]);
        })
      }
    })
  }

  verifyFieldsBeforeSubmit(): boolean {
    let textControl = this.form.get('text');
    let typeControl = this.form.get('type')
    if (textControl && typeControl) {
      let text = this.form.get('text')?.value;
      let type = this.form.get('type')?.value;
      if (text === '' || type === '') {
        this.activeModalText("Preencha todos os campos antes de enviar a mensagem!");
        return false;
      }
    }
    return true;
  }

  clearTextarea(): void {
    this.form.patchValue({
      text: ''
    })
  }

  activeModalText(text: string):void {
    this.showModalText = false; //reset
    setTimeout(() => {
      this.message = text;
      this.showModalText = true;
      this.cdr.detectChanges();
    });
  }

  waitForModalClose(): Promise<void> {
    return new Promise(resolve => {
      this.modalComponent.onClose.subscribe(() => resolve());
    })
  }
}
