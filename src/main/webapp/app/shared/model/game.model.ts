export interface IGame {
  id?: string;
  name?: string | null;
  description?: string | null;
  rating?: number | null;
}

export const defaultValue: Readonly<IGame> = {};
