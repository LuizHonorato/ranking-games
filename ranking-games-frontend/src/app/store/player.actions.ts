import { Action } from "@ngrx/store";

interface Player {
  id: Number;
  name: String;
  matches: Number;
  victories: Number;
}

export enum PlayerActionTypes {
  PLAYER_ALL = "[PLAYER_ALL] Get all players",
  LOAD_PLAYER_SUCCESS = "[LOAD_PLAYER_SUCCESS] Players Loaded Successfully",
  PLAYER_NEW = "[PLAYER_NEW] Add a new player",
  PLAYER_UPDATE = "[PLAYER_UPDATE] Update a player",
  PLAYER_DELETE = "[PLAYER_DELETE] Delete a player"
}

export class PlayerAll implements Action {
  readonly type = PlayerActionTypes.PLAYER_ALL;
}

export class LoadSuccess implements Action {
  readonly type = PlayerActionTypes.LOAD_PLAYER_SUCCESS;
  constructor(public payload: Player[]) {}
}

export class PlayerNew implements Action {
  readonly type = PlayerActionTypes.PLAYER_NEW;
  constructor(public payload: Player) {}
}

export class PlayerUpdate implements Action {
  readonly type = PlayerActionTypes.PLAYER_UPDATE;
  constructor(public payload: Player) {}
}

export class PlayerDelete implements Action {
  readonly type = PlayerActionTypes.PLAYER_DELETE;
  constructor(public payload: Player) {}
}

export type PlayerActions =
  | PlayerAll
  | LoadSuccess
  | PlayerNew
  | PlayerUpdate
  | PlayerDelete;
