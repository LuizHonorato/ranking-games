// modules
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";

// components
import { PageNotFoundComponent } from "./not-found/not-found.component";

// services
import { HttpClientModule } from "@angular/common/http";
import { StoreModule } from "@ngrx/store";
import { EffectsModule } from "@ngrx/effects";
import { ExtractNamesPipe } from "./extract-names.pipe";

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    StoreModule,
    EffectsModule
  ],
  declarations: [PageNotFoundComponent, ExtractNamesPipe],
  providers: [],
  exports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    StoreModule,
    EffectsModule,
    PageNotFoundComponent,
    ExtractNamesPipe
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SharedModule {}
