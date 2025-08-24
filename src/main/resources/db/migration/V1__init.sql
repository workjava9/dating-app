create table if not exists users (
  id uuid primary key,
  email varchar(255) unique not null,
  password varchar(255) not null,
  name varchar(255),
  created_at timestamp not null default now()
);
create table if not exists profiles (
  id uuid primary key,
  user_id uuid not null references users(id) on delete cascade,
  bio text,
  interests text
);
