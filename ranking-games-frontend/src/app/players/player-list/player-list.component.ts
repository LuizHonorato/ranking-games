import { Component, OnInit } from "@angular/core";
import { Store } from "@ngrx/store";
import { AppState } from "../../app.state";
import { Player } from "../shared/player";
import { Observable } from "rxjs";
import * as playerActions from "../store/players.actions";
import { getAllPlayers } from "../store/players.reducers";

@Component({
  selector: "app-player-list",
  templateUrl: "./player-list.component.html",
  styleUrls: ["./player-list.component.css"]
})
export class PlayerListComponent implements OnInit {
  title = "Lista de jogadores";
  players: Observable<Player[]>;

  constructor(private store: Store<AppState>) {}

  ngOnInit() {
    this.players = this.store.select(getAllPlayers);
  }

  delete(id: number) {
    if (confirm("Deseja deletar esse jogador?")) {
      this.store.dispatch(new playerActions.RemovePlayer(id));
    }
  }
}
