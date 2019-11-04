import { NgModule } from "@angular/core";

import { RouterModule, Routes } from "@angular/router";
import { PlayersComponent } from "./players.component";
import { PlayerListComponent } from "./player-list/player-list.component";
import { PlayerCreateComponent } from "./player-create/player-create.component";
import { PlayerEditComponent } from "./player-edit/player-edit.component";

export const playersRoutes: Routes = [
  {
    path: "",
    component: PlayersComponent,
    children: [
      { path: "", component: PlayerListComponent },
      { path: "create", component: PlayerCreateComponent },
      { path: "edit/:id", component: PlayerEditComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(playersRoutes)],
  exports: [RouterModule]
})
export class PlayersRoutingModule {}

export const playersRoutedComponents = [
  PlayersComponent,
  PlayerListComponent,
  PlayerCreateComponent,
  PlayerEditComponent
];
