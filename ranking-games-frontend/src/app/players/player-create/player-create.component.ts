import { Component, OnInit } from "@angular/core";
import { Player } from "../shared/player";
import { AppState } from "../../app.state";
import { Store } from "@ngrx/store";
import { AddPlayer } from "../store/players.actions";
import { Router } from "@angular/router";

@Component({
  selector: "app-player-create",
  templateUrl: "./player-create.component.html",
  styleUrls: ["./player-create.component.css"]
})
export class PlayerCreateComponent implements OnInit {
  title = "Novo jogador";
  player: Player = new Player();

  constructor(private router: Router, private store: Store<AppState>) {}

  ngOnInit() {}

  onBack() {
    this.router.navigate(["/players"]);
  }

  onSavePlayer() {
    this.store.dispatch(new AddPlayer(this.player));
  }

  reset() {
    this.player.name = "";
    this.player.matches = 0;
    this.player.victories = 0;
  }
}
