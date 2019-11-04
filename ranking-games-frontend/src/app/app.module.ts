import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppComponent } from "./app.component";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RankingModule } from "./ranking.module";
import { PlayerComponent } from "./player/player.component";
import { StoreModule } from "@ngrx/store";
import { StoreDevtoolsModule } from "@ngrx/store-devtools";
import { EffectsModule } from "@ngrx/effects";
import { PlayerEffects } from "./store/effects";
import { PlayerReducer } from "./store/player.reducer";
import { HttpClientModule } from "@angular/common/http";

@NgModule({
  declarations: [AppComponent, PlayerComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    RankingModule,
    StoreModule.forRoot({ player: PlayerReducer }),
    StoreDevtoolsModule.instrument({
      maxAge: 25
    }),
    EffectsModule.forRoot([PlayerEffects])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
