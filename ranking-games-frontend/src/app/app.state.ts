import * as fromPlayers from "./players/store/players.reducers";

export interface AppState {
  players: fromPlayers.State;
}
