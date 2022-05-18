create table public.student
(
    id          character varying(36) not null unique,
    deleted     boolean               not null default false,
    created_at  timestamp             not null default current_timestamp,
    modified_at timestamp             not null default current_timestamp,
    email       character varying(255),
    password    character varying(255),
    constraint pk_student primary key (id)
);

create table public.supervisor
(
    id          character varying(36) not null unique,
    deleted     boolean               not null default false,
    created_at  timestamp             not null default current_timestamp,
    modified_at timestamp             not null default current_timestamp,
    first_name  character varying(255),
    last_name   character varying(255),
    email       character varying(255),
    student_id  character varying(36) not null,
    constraint pk_supervisor primary key (id),
    constraint fk_sup_stu foreign key (student_id) references public.student (id)
);

create table public.school_class
(
    id          character varying(36) not null unique,
    deleted     boolean               not null default false,
    created_at  timestamp             not null default current_timestamp,
    modified_at timestamp             not null default current_timestamp,
    name        character varying(255),
    constraint pk_school_class primary key (id)
);

create table public.student_school_class
(
    id              character varying(36) not null unique,
    deleted         boolean               not null default false,
    created_at      timestamp             not null default current_timestamp,
    modified_at     timestamp             not null default current_timestamp,
    student_id      character varying(36) not null,
    school_class_id character varying(36) not null,
    constraint pk_student_school_class primary key (id),
    constraint fk_ssc_stu foreign key (student_id) references public.student (id),
    constraint fk_ssc_sc foreign key (school_class_id) references public.school_class (id),
    constraint ssc_stu_id_sc_id unique (student_id,
                                        school_class_id)
);

create table public.subject
(
    id          character varying(36) not null unique,
    deleted     boolean               not null default false,
    created_at  timestamp             not null default current_timestamp,
    modified_at timestamp             not null default current_timestamp,
    name        character varying(255),
    constraint pk_subject primary key (id)
);

create table public.semester
(
    id          character varying(36) not null unique,
    deleted     boolean               not null default false,
    created_at  timestamp             not null default current_timestamp,
    modified_at timestamp             not null default current_timestamp,
    year        integer,
    season      character varying(255),
    constraint pk_semester primary key (id),
    constraint sem_year_season unique (year,
                                       season)
);

create table public.school_class_subject_semester
(
    id              character varying(36) not null unique,
    deleted         boolean               not null default false,
    created_at      timestamp             not null default current_timestamp,
    modified_at     timestamp             not null default current_timestamp,
    school_class_id character varying(36) not null,
    subject_id      character varying(36) not null,
    semester_id     character varying(36) not null,
    constraint pk_school_class_subject_semester primary key (id),
    constraint fk_scss_sc foreign key (school_class_id) references public.school_class (id),
    constraint fk_scss_sub foreign key (subject_id) references public.subject (id),
    constraint fk_scss_sem foreign key (semester_id) references public.semester (id),
    constraint scss_sc_id_sub_id_sem_id unique (school_class_id,
                                                subject_id,
                                                semester_id)
);

create table public.exam
(
    id                               character varying(36) not null unique,
    deleted                          boolean               not null default false,
    created_at                       timestamp             not null default current_timestamp,
    modified_at                      timestamp             not null default current_timestamp,
    date                             date,
    name                             character varying(255),
    weight                           double precision,
    school_class_subject_semester_id character varying(36) not null,
    constraint pk_exam primary key (id),
    constraint fk_exa_scss foreign key (school_class_subject_semester_id) references public.school_class_subject_semester (id)
);

create table public.grade
(
    id          character varying(36) not null unique,
    deleted     boolean               not null default false,
    created_at  timestamp             not null default current_timestamp,
    modified_at timestamp             not null default current_timestamp,
    mark        double precision,
    exam_id     character varying(36) not null,
    student_id  character varying(36) not null,
    constraint pk_grade primary key (id),
    constraint fk_gra_exa foreign key (exam_id) references public.exam (id),
    constraint fk_gra_stu foreign key (student_id) references public.student (id),
    constraint gra_exa_id_stu_id unique (exam_id,
                                         student_id)
);