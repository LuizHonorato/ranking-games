import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

// components
import { PageNotFoundComponent } from "./shared/not-found/not-found.component";

const routes: Routes = [
  { path: "", redirectTo: "/players", pathMatch: "full" },
  {
    path: "players",
    loadChildren: "./players/players.module#PlayersModule"
  },
  { path: "**", component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule {}
