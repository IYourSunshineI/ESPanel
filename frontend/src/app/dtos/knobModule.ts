
export interface KnobModule {
  id?: number;
  title: string;
  pinNumber: number;
  group_id?: number;
}

export interface RgbModule extends KnobModule {
  color: string;
}

export interface DimmerModule extends KnobModule {
  brightness: number;
}
