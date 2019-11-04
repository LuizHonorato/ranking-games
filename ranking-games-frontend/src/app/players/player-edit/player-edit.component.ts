import { Component, OnInit } from "@angular/core";
import { Player } from "../shared/player";
import { ActivatedRoute, Router } from "@angular/router";
import { AppState } from "../../app.state";
import { Store } from "@ngrx/store";
import * as playerActions from "../store/players.actions";
import { GetPlayer, UpdatePlayer } from "../store/players.actions";
import { getPlayer } from "../store/players.reducers";

@Component({
  selector: "app-player-edit",
  templateUrl: "./player-edit.component.html",
  styleUrls: ["./player-edit.component.css"]
})
export class PlayerEditComponent implements OnInit {
  title = "Editando jogar";
  player: Player;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private store: Store<AppState>
  ) {}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.store.dispatch(new GetPlayer(+params.id));
    });

    this.store.select(getPlayer).subscribe(player => {
      if (player != null) {
        this.player = player;
      }
    });
  }

  onSavePlayer() {
    this.store.dispatch(new UpdatePlayer(this.player));
  }

  onBack() {
    this.router.navigate(["/players"]);
  }

  reset() {
    this.player.name = "";
    this.player.matches = 0;
    this.player.victories = 0;
  }

  delete(id: number) {
    if (confirm("Você deseja deletar esse jogador?")) {
      this.store.dispatch(new playerActions.RemovePlayer(id));
    }
  }
}
