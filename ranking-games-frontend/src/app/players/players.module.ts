import { NgModule } from "@angular/core";
import { PlayersService } from "./shared/players.service";
import {
  playersRoutedComponents,
  PlayersRoutingModule
} from "./players-routing.module";
import { SharedModule } from "../shared/shared.module";

// ngrx elements
import { StoreModule, ActionReducerMap } from "@ngrx/store";
import { EffectsModule } from "@ngrx/effects";
import { PlayerEffects } from "./store/players.effects";
import * as playerReducer from "./store/players.reducers";

export const reducers: ActionReducerMap<any> = {
  players: playerReducer.reducer
};

@NgModule({
  imports: [
    SharedModule,
    PlayersRoutingModule,
    StoreModule.forRoot(reducers),
    EffectsModule.forRoot([PlayerEffects])
  ],
  declarations: [playersRoutedComponents],
  providers: [PlayersService]
})
export class PlayersModule {}
