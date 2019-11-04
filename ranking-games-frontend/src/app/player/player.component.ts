import { Component, OnInit, Input, Output, EventEmitter } from "@angular/core";
import { Store } from "@ngrx/store";

export interface Player {
  id: Number;
  name: String;
  matches: Number;
  victories: Number;
}

@Component({
  selector: "app-player",
  templateUrl: "./player.component.html",
  styleUrls: ["./player.component.css"]
})
export class PlayerComponent implements OnInit {
  constructor() {}

  @Input() players: Player[] = [];

  ngOnInit() {}
}
