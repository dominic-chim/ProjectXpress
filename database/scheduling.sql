-- table project stores project information
CREATE TABLE project (
project_id int unsigned  auto_increment,   
project_name varchar(250),
project_due_date datetime,
project_priority int unsigned ,
project_status varchar(20),
primary key (project_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- table risk_level shows risk level name and the percentage of that name 
-- must create before task since foreign key restraint
CREATE TABLE risk_level (
task_risk_level varchar(20),
risk_percentage int,
primary key (task_risk_level)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- table skill stores skill id and 
-- must create before task since foreign key restraint
CREATE TABLE skill (
skill_id int unsigned auto_increment,
skill_name char(50),
primary key (skill_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


-- table task stores task information for every table
CREATE TABLE task (
project_id int unsigned ,
task_id int unsigned ,
task_name char(100) ,
task_required_skill int unsigned,
task_duration int unsigned ,
task_risk_level varchar(20),
task_release_time datetime,
task_status char(20),
task_remaining_time int unsigned,
primary key (project_id, task_id),
foreign key (project_id) references project (project_id),
foreign key (task_required_skill) references skill (skill_id),
foreign key (task_risk_level) references risk_level (task_risk_level)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- table task_precedence shows preconditions of each task
CREATE TABLE task_precedence (
project_id int unsigned ,
task_id int unsigned ,
required_task_id int unsigned ,
primary key (project_id, task_id, required_task_id),
foreign key (project_id, task_id) references task (project_id, task_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- table staff stores the information of each staff
CREATE TABLE staff (
staff_id int unsigned  auto_increment,
staff_name char(80),
staff_weekly_available_time int unsigned ,
status char(15) DEFAULT 'Active',
primary key (staff_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- table staff_skill_level stores levels of all skills of each staff
CREATE TABLE staff_skill_level (
staff_id int unsigned ,
skill_id int unsigned,
skill_level real,
primary key (staff_id, skill_id),
foreign key (staff_id) references staff (staff_id),
foreign key (skill_id) references skill (skill_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
    
-- table staff_preference shows staff's preference of each task
CREATE TABLE staff_preference (
staff_id int unsigned ,
project_id int unsigned ,
task_id int unsigned ,
preference_level int unsigned ,
primary key (staff_id, project_id, task_id),
foreign key (staff_id) references staff (staff_id),
foreign key (project_id, task_id) references task (project_id, task_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- table historical_holiday_record shows average taken holidays based on historical record
CREATE TABLE historical_holiday_record (
month int unsigned ,
holiday_count int unsigned ,
primary key (month)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- table staff_holidays shows staff holidays allocation
CREATE TABLE staff_holidays (
staff_id int unsigned ,
holiday_start_time datetime,
holiday_end_time datetime,
primary key (staff_id, holiday_start_time),
foreign key (staff_id) references staff (staff_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

    
-- table scheduling_result stores the result
CREATE TABLE scheduling_result (
staff_id int unsigned ,
start_datetime datetime,
end_datetime datetime,
project_id int unsigned ,
task_id int unsigned ,
version int unsigned,
primary key (staff_id, start_datetime, version),
foreign key (project_id, task_id) references task (project_id, task_id),
foreign key (staff_id) references staff (staff_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
