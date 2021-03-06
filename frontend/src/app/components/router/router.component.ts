import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NbSidebarService } from '@nebular/theme';
import { NgxSpinnerService } from 'ngx-spinner';
import { Workspace } from 'src/app/models/workspace';

@Component({
  selector: 'app-router',
  templateUrl: './router.component.html',
  styleUrls: ['./router.component.scss']
})
export class RouterComponent implements OnInit {

  workspaces: Workspace[] = [];

  constructor(private route: ActivatedRoute, private spinner: NgxSpinnerService, private sidebarService: NbSidebarService) { }

  ngOnInit(): void {
    this.workspaces = this.route.snapshot.data.workspaces;
    this.spinner.hide();
  }

  toggle() {
    this.sidebarService.toggle(false);
  }

}
