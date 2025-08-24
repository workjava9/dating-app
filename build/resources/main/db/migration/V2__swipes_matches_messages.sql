create table if not exists swipes (
  id uuid primary key,
  from_user uuid not null references users(id) on delete cascade,
  to_user uuid not null references users(id) on delete cascade,
  liked boolean not null,
  created_at timestamp not null default now(),
  unique (from_user, to_user)
);

create table if not exists matches (
  id uuid primary key,
  user1 uuid not null references users(id) on delete cascade,
  user2 uuid not null references users(id) on delete cascade,
  created_at timestamp not null default now()
);

create table if not exists messages (
  id uuid primary key,
  match_id uuid not null references matches(id) on delete cascade,
  from_user uuid not null references users(id) on delete cascade,
  text text not null,
  created_at timestamp not null default now()
);
