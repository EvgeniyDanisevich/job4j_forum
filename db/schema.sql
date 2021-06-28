create table topic (
    id serial primary key,
    name varchar(255),
    description text,
    created timestamp without time zone not null default now()
);
create table post (
    id serial primary key,
    text text,
    created timestamp without time zone not null default now(),
    topic_id int,
    foreign key (topic_id) references topic (id)
);

create table topic_post (
    topic_id int not null,
    post_id int not null,
    foreign key (topic_id) references topic (id),
    foreign key (post_id) references post (id)
);