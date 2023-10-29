import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./components/login/login.component";
import {SearchStudentComponent} from "./components/search-student/search-student.component";
import {SaveStudentComponent} from "./components/save-student/save-student.component";
import {LogoutComponent} from "./components/logout/logout.component";
import {DashboardComponent} from "./components/dashboard/dashboard.component";
import {ErrorPageComponent} from "./components/error-page/error-page.component";

const routes: Routes = [
  {path: "", component: LoginComponent},
  {path: "login", component: LoginComponent},
  {path: "dashboard", component: DashboardComponent},
  {path: "search", component: SearchStudentComponent},
  {path: "save", component: SaveStudentComponent},
  {path: "logout", component: LogoutComponent},
  {path: "**", component: ErrorPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
