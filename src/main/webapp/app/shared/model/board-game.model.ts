import dayjs from 'dayjs';

export interface IBoardGame {
  id?: string;
  englishName?: string;
  chineseName?: string;
  description?: string | null;
  yearPublished?: number | null;
  minPlayers?: number | null;
  maxPlayers?: number | null;
  playTimeMin?: number | null;
  playTimeMax?: number | null;
  minAge?: number | null;
  suggestedAge?: number | null;
  mechanics?: string;
  categories?: string;
  publishers?: string | null;
  rating?: number | null;
  ratingVotes?: number | null;
  complexity?: number | null;
  complexityVotes?: number | null;
  officialUrl?: string | null;
  bggUrl?: string | null;
  gstoneUrl?: string | null;
  thumbnailUrl?: string | null;
  largeImageUrl?: string | null;
  createdAt?: dayjs.Dayjs | null;
  updatedAt?: dayjs.Dayjs | null;
}

export const defaultValue: Readonly<IBoardGame> = {};
