drop table if exists public.grade;
drop table if exists public.exam;
drop table if exists public.school_class_semester_subject;
drop table if exists public.school_class_semester;
drop table if exists public.student_school_class;
drop table if exists public.school_class;
drop table if exists public.supervisor;
drop table if exists public.student;
drop table if exists public.subject;
drop table if exists public.semester;

create table public.student
(
    id               character varying(36) not null unique,
    deleted          boolean               not null default false,
    created_at       timestamp             not null default current_timestamp,
    modified_at      timestamp             not null default current_timestamp,
    created_by       character varying(36),
    last_modified_by character varying(36),
    email            character varying(255),
    password         character varying(255),
    first_name       character varying(255),
    constraint pk_student primary key (id)
);

create table public.supervisor
(
    id               character varying(36) not null unique,
    deleted          boolean               not null default false,
    created_at       timestamp             not null default current_timestamp,
    modified_at      timestamp             not null default current_timestamp,
    created_by       character varying(36),
    last_modified_by character varying(36),
    first_name       character varying(255),
    last_name        character varying(255),
    email            character varying(255),
    student_id       character varying(36) not null,
    constraint pk_supervisor primary key (id),
    constraint fk_sup_stu foreign key (student_id) references public.student (id)
);

create table public.school_class
(
    id               character varying(36) not null unique,
    deleted          boolean               not null default false,
    created_at       timestamp             not null default current_timestamp,
    modified_at      timestamp             not null default current_timestamp,
    created_by       character varying(36),
    last_modified_by character varying(36),
    name             character varying(255),
    start_year       int                   not null,
    end_year         int                   not null,
    constraint pk_school_class primary key (id)
);

create table public.student_school_class
(
    id               character varying(36) not null unique,
    deleted          boolean               not null default false,
    created_at       timestamp             not null default current_timestamp,
    modified_at      timestamp             not null default current_timestamp,
    created_by       character varying(36),
    last_modified_by character varying(36),
    student_id       character varying(36) not null,
    school_class_id  character varying(36) not null,
    constraint pk_student_school_class primary key (id),
    constraint fk_ssc_stu foreign key (student_id) references public.student (id),
    constraint fk_ssc_sc foreign key (school_class_id) references public.school_class (id),
    constraint ssc_stu_id_sc_id unique (student_id,
                                        school_class_id, deleted)
);

create table public.subject
(
    id               character varying(36) not null unique,
    deleted          boolean               not null default false,
    created_at       timestamp             not null default current_timestamp,
    modified_at      timestamp             not null default current_timestamp,
    created_by       character varying(36),
    last_modified_by character varying(36),
    name             character varying(255),
    constraint pk_subject primary key (id)
);

create table public.semester
(
    id               character varying(36) not null unique,
    deleted          boolean               not null default false,
    created_at       timestamp             not null default current_timestamp,
    modified_at      timestamp             not null default current_timestamp,
    created_by       character varying(36),
    last_modified_by character varying(36),
    start_date       date                  not null unique,
    constraint pk_semester primary key (id)
);

create table public.school_class_semester
(
    id               character varying(36) not null unique,
    deleted          boolean               not null default false,
    created_at       timestamp             not null default current_timestamp,
    modified_at      timestamp             not null default current_timestamp,
    created_by       character varying(36),
    last_modified_by character varying(36),
    school_class_id  character varying(36) not null,
    semester_id      character varying(36) not null,
    constraint pk_school_class_semester primary key (id),
    constraint fk_scs_sc foreign key (school_class_id) references public.school_class (id),
    constraint fk_scs_sem foreign key (semester_id) references public.semester (id),
    constraint scs_sc_id_sem_id unique (school_class_id,
                                        semester_id, deleted)
);

create table public.school_class_semester_subject
(
    id                       character varying(36) not null unique,
    deleted                  boolean               not null default false,
    created_at               timestamp             not null default current_timestamp,
    modified_at              timestamp             not null default current_timestamp,
    created_by               character varying(36),
    last_modified_by         character varying(36),
    school_class_semester_id character varying(36) not null,
    subject_id               character varying(36) not null,
    constraint pk_school_class_semester_subject primary key (id),
    constraint fk_scss_scs foreign key (school_class_semester_id) references public.school_class_semester (id),
    constraint fk_scss_sub foreign key (subject_id) references public.subject (id),
    constraint scss_scs_id_sub_id unique (school_class_semester_id,
                                          subject_id, deleted)
);

create table public.exam
(
    id                               character varying(36) not null unique,
    deleted                          boolean               not null default false,
    created_at                       timestamp             not null default current_timestamp,
    modified_at                      timestamp             not null default current_timestamp,
    created_by                       character varying(36),
    last_modified_by                 character varying(36),
    date                             date,
    name                             character varying(255),
    weight                           double precision,
    school_class_semester_subject_id character varying(36) not null,
    constraint pk_exam primary key (id),
    constraint fk_exa_scss foreign key (school_class_semester_subject_id) references public.school_class_semester_subject (id)
);

create table public.grade
(
    id               character varying(36) not null unique,
    deleted          boolean               not null default false,
    created_at       timestamp             not null default current_timestamp,
    modified_at      timestamp             not null default current_timestamp,
    created_by       character varying(36),
    last_modified_by character varying(36),
    mark             double precision,
    exam_id          character varying(36) not null,
    student_id       character varying(36) not null,
    constraint pk_grade primary key (id),
    constraint fk_gra_exa foreign key (exam_id) references public.exam (id),
    constraint fk_gra_stu foreign key (student_id) references public.student (id),
    constraint gra_exa_id_stu_id unique (exam_id,
                                         student_id, deleted)
);

insert into public.student (id,
                            email,
                            password)
values ('student_1',
        'student_1@email.com',
        '$2a$10$ijlUSk9zjcMLXbe8FbZNHOo4Uh4DozIZxgcoc.vM7JrkkF2pY6sX6'),
       ('student_2',
        'student_2@email.com',
        '$2a$10$ijlUSk9zjcMLXbe8FbZNHOo4Uh4DozIZxgcoc.vM7JrkkF2pY6sX6'),
       ('student_3',
        'student_3@email.com',
        '$2a$10$ijlUSk9zjcMLXbe8FbZNHOo4Uh4DozIZxgcoc.vM7JrkkF2pY6sX6'),
       ('student_4',
        'student_4@email.com',
        '$2a$10$ijlUSk9zjcMLXbe8FbZNHOo4Uh4DozIZxgcoc.vM7JrkkF2pY6sX6');

insert into public.semester (id,
                             start_date)
values ('semester_1',
        '2018-08-01'),
       ('semester_2',
        '2019-02-02'),
       ('semester_3',
        '2019-08-01'),
       ('semester_4',
        '2020-02-01'),
       ('semester_5',
        '2020-08-01'),
       ('semester_6',
        '2021-02-01'),
       ('semester_7',
        '2021-08-01'),
       ('semester_8',
        '2022-02-01');

insert into public.school_class (id,
                                 name,
                                 start_year,
                                 end_year)
values ('school_class_1',
        'AP18a',
        2018,
        2022);

insert into public.subject (id,
                            name)
values ('subject_1',
        'M 151 - Datenbanken in Web-Applikationen einbinden'),
       ('subject_2',
        'M 183 - Applikationssicherheit implementieren');

insert into public.school_class_semester (id,
                                          school_class_id,
                                          semester_id)
values ('school_class_semester_1',
        'school_class_1',
        'semester_1'),
       ('school_class_semester_2',
        'school_class_1',
        'semester_2'),
       ('school_class_semester_3',
        'school_class_1',
        'semester_3'),
       ('school_class_semester_4',
        'school_class_1',
        'semester_4'),
       ('school_class_semester_5',
        'school_class_1',
        'semester_5'),
       ('school_class_semester_6',
        'school_class_1',
        'semester_6'),
       ('school_class_semester_7',
        'school_class_1',
        'semester_7'),
       ('school_class_semester_8',
        'school_class_1',
        'semester_8');

insert into public.school_class_semester_subject (id,
                                                  school_class_semester_id,
                                                  subject_id)
values ('school_class_semester_subject_1',
        'school_class_semester_5',
        'subject_1'),
       ('school_class_semester_subject_2',
        'school_class_semester_5',
        'subject_2');

insert into public.exam (id,
                         date,
                         name,
                         weight,
                         school_class_semester_subject_id)
values ('exam_1',
        '2020-11-01',
        'Abschluss-Projekt',
        50,
        'school_class_semester_subject_1'),
       ('exam_2',
        '2020-11-01',
        'OWASP Präsentation',
        50,
        'school_class_semester_subject_2'),
       ('exam_3',
        '2020-11-01',
        'Abschluss Test',
        50,
        'school_class_semester_subject_2');

insert into public.grade (id,
                          mark,
                          exam_id,
                          student_id)
values ('grade_1',
        6.0,
        'exam_1',
        'student_1'),
       ('grade_2',
        6.0,
        'exam_2',
        'student_1'),
       ('grade_3',
        5.0,
        'exam_1',
        'student_2'),
       ('grade_4',
        5.0,
        'exam_2',
        'student_2'),
        ('grade_5',
        4.5,
        'exam_3',
        'student_1');

insert into public.student_school_class (id,
                                         student_id,
                                         school_class_id)
values ('student_school_class_1',
        'student_1',
        'school_class_1'),
       ('student_school_class_2',
        'student_2',
        'school_class_1');
