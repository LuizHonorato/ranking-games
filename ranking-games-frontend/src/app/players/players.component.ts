import { Component, OnInit } from "@angular/core";
import { Store } from "@ngrx/store";
import { Router } from "@angular/router";
import { AppState } from "../app.state";
import { GetAllPlayers } from "./store/players.actions";
import {
  getCreateError,
  getDeleteError,
  getPlayersError,
  getUpdateError,
  isCreated,
  isDeleted,
  isUpdated
} from "./store/players.reducers";

@Component({
  selector: "app-player",
  template: `
    <router-outlet></router-outlet>
  `,
  styleUrls: ["./players.component.css"]
})
export class PlayersComponent implements OnInit {
  constructor(private router: Router, private store: Store<AppState>) {}

  ngOnInit() {
    console.log("... Initializing Players component");
    this.store.dispatch(new GetAllPlayers());

    // subscriptions when success or error action
    this.store
      .select(getPlayersError)
      .subscribe(error => this.loadingError(error));
    this.store.select(isDeleted).subscribe(done => {
      this.actionSuccess(done, "O jogador foi deleteado com sucesso!!!");
    });
    this.store.select(getDeleteError).subscribe(error => {
      this.actionError(error, "Erro ao deletar o jogador");
    });
    this.store.select(isUpdated).subscribe(done => {
      this.actionSuccess(done, "The jogador foi atualizado com sucesso!!!");
    });
    this.store.select(getUpdateError).subscribe(error => {
      this.actionError(error, "Erro ao atualizar o jogador");
    });
    this.store.select(isCreated).subscribe(done => {
      this.actionSuccess(done, "O jogador foi criado com sucesso!!!");
    });
    this.store.select(getCreateError).subscribe(error => {
      this.actionError(error, "Erro ao criar o jogador");
    });
  }

  loadingError(error) {
    if (error) {
      alert("Erro ao carregar a lista dos jogadores");
    }
  }

  actionSuccess(done: boolean, message: string) {
    if (done) {
      alert(message);
      this.router.navigate(["/players"]);
    }
  }

  actionError(error, message: string) {
    if (error) {
      alert(message);
    }
  }
}
