import { SysAdminService } from './../../services/SysAdminService';
import { AuthService } from './../../services/AuthService';
import { ComplaintDTO } from './../../model/ComplaintDTO';
import { ActivatedRoute, Router } from '@angular/router';
import { MatDialogRef, MatTableDataSource } from '@angular/material';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/UserService';
import { UserDTO } from 'src/app/model/UserDTO';
import { ComplaintPreview } from 'src/app/model/ComplaintPreview';
import { Reply } from 'src/app/model/Reply';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent implements OnInit {

  complaintForm : FormGroup;
  complaint : ComplaintDTO;
  id: number;

  dataSource: MatTableDataSource<ComplaintPreview>;
  fetchedComplaints : ComplaintPreview[] = [];
  displayedColumns : string[]=[
    'id',
    'userid',
    'name',
    'text',
    'status',
    'action'
  ];



  isPatient = false;
  anyLogged = false;
  isSysAdmin= false;
  showAnswerForm = false;
  profil : UserDTO;
  notReplied = true;
  showTable = false;

  //reply
  replyForm : FormGroup;
  userid: number;
  comid: number;
  replyModel : Reply;

  constructor(
    private formBuilder : FormBuilder,
    public userService : UserService,
    private route : ActivatedRoute,
    private router : Router,
    private authService : AuthService,
    private sysAdminService : SysAdminService
  ) { }

  ngOnInit() {

    this.id = this.route.snapshot.params.id;


    this.complaintForm = new FormGroup({
      id: new FormControl({value: this.id, disabled : true}, [Validators.required]),
      text : new FormControl('', [Validators.required])

    });

    this.replyForm = new FormGroup({
      comid: new FormControl({value: this.comid, disabled: true}, [Validators.required]),
      userid: new FormControl({value: this.userid, disabled: true}, [Validators.required]),
      answer: new FormControl('', [Validators.required])
    });


    this.authService.getLogged().subscribe(response => {
      this.profil = response;
      const role = this.profil.authorities[0];

      if(role == 'ROLE_PATIENT') {
        this.isPatient = true;
        this.anyLogged= true;

      } else {
        this.isPatient = false;
      }
      if(role == 'ROLE_SYS_ADMIN'){
        this.isSysAdmin = true;
        this.anyLogged = true;
        this.showTable = true;
        this.fetchComplaints();
      } else {
        this.isSysAdmin = false;
      }
    });

  }

  backToHistory(){
    this.router.navigate(['/history-of-app-d']);
  }

  sendComplaint(){

    this.complaint = new ComplaintDTO(
      this.complaintForm.get('id').value,
      this.complaintForm.get('text').value
    );

    alert(this.complaintForm.get('text').value);
    console.log(this.complaint);

      this.sysAdminService.sendComplaint(this.complaint).subscribe(response => {
        alert("The complaint has been sent to system administrator. Check your e-mail for answer!");
        this.router.navigate(['history-of-app-d']);
      });

  }

   fetchComplaints(){

        this.sysAdminService.getAllComplaints().subscribe(response => {
          this.fetchedComplaints = response;
          this.dataSource = new MatTableDataSource(this.fetchedComplaints);

        });

   }

   reply(cid: number, id:number, status: string){

    if(status == 'REVIEWED'){
      alert("This complaint has been reviewed!");
      return;
    }
       this.showAnswerForm = true;
       this.comid = cid;
       this.userid = id;
   }

   cancel(){
     this.showAnswerForm = false;
   }
   sendReply(){



    this.replyModel = new Reply(
      this.replyForm.get('comid').value,
      this.replyForm.get('userid').value,
      this.replyForm.get('answer').value
    );

    this.sysAdminService.sendReply(this.replyModel).subscribe(response => {
      alert("Reply sent!");
      this.showAnswerForm = false;
      this.fetchComplaints();

    });
   }

}
