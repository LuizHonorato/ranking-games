import { Component, OnInit } from "@angular/core";
import { PlayerAll } from "./store/player.actions";
import { select, Store } from "@ngrx/store";
import { Player } from "./player/player.component";

@Component({
  selector: "app-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.css"]
})
export class AppComponent implements OnInit {
  constructor(private store: Store<{ players: Player[] }>) {
    store
      .pipe(select("player"))
      .subscribe(data => (this.players = data.players));
  }

  players: Player[] = [];

  ngOnInit(): void {
    this.store.dispatch(new PlayerAll());
  }
}
