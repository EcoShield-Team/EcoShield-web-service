-- ===== ROLES =====
INSERT INTO rol (rolnombre, roldescripcion) VALUES ('USER', 'Rol por defecto de usuarios');
INSERT INTO rol (rolnombre, roldescripcion) VALUES ('ADMIN', 'Rol por defecto para administradores');

-- ===== USUARIOS =====
INSERT INTO usuario (rolid, usuarionombre, usuariocorreo, usuariocontrasena, usuarioestado, usuariofotoperfil, usuariopais, usuariofecharegistro)VALUES(2, 'Admin', 'admin@gmail.com', '$2a$12$rpcw1mC92T6qRn8dHaY6X.xquIhHAmG05y8KxJYjpTN5GAfGxaKT2', 'ACTIVO', NULL, 'PERU', NOW()),(1, 'Gerardo Chávez', 'gerardo@gmail.com', '$2a$12$CsETsiaFMgl4hPKKHQgkF.IUZF3PjdwChi0rvO.lpJNLhBCDmyl7C', 'ACTIVO', NULL, 'PERU', NOW()),(1, 'María López', 'maria@gmail.com', '$2a$12$/0MDLXXRq84tCb2O1IfvdeBDwS7e3RCmOR72.Jb96KE2YYPQAUTda', 'ACTIVO', NULL, 'PERU', NOW()),(1, 'Carlos Pérez', 'carlos@gmail.com', '$2a$12$r7QUrw90vXQ7HoI5s5B2Zu.8t1QbexRtcphe/OSIuEGC5CNFLl.BC', 'ACTIVO', NULL, 'CHILE', NOW()),(1, 'Luis Fernández', 'luis@gmail.com', '$2a$12$DtL3l8suoBUo2PAqx6L2yeiUBaIMeVLnz8Pz/Rhld0Lg06JHb5.ze', 'INACTIVO', NULL, 'ARGENTINA', NOW());

-- ===== PLAGAS =====
INSERT INTO plaga (plaganombre, plaganombrecientifico, plagatipo, plagadescripcion, plagasintomas, plagatratamiento, plagacausas, plagaprevenciones, plagafoto, temporada, severidad)VALUES('Pulgón', 'Aphididae', 'INSECTO', 'Pequeños insectos que chupan la savia y debilitan la planta.', 'Hojas deformadas y melaza pegajosa.', 'Aplicación de jabón potásico.', 'Altas temperaturas y exceso de nitrógeno.', 'Podas preventivas y control biológico.', 'https://example.com/img/pulgon.jpg', 'PRIMAVERA', 'MODERADA'),('Mosca blanca', 'Trialeurodes vaporariorum', 'INSECTO', 'Insecto volador que causa amarillamiento foliar.', 'Hojas con puntos amarillos y decaimiento.', 'Uso de trampas cromáticas y neem.', 'Ambiente cálido y seco.', 'Evitar hacinamiento de plantas.', 'https://example.com/img/moscablanca.jpg', 'VERANO', 'GRAVE'),('Cochinilla', 'Coccoidea', 'INSECTO', 'Parasita tallos y hojas con secreciones algodonosas.', 'Hojas pegajosas y crecimiento detenido.', 'Aceite mineral o jabón potásico.', 'Alta humedad.', 'Limpieza de hojas y control de hormigas.', 'https://example.com/img/cochinilla.jpg', 'OTOÑO', 'LEVE');

-- ===== ENFERMEDADES =====
INSERT INTO enfermedad (enfermedadnombre, enfermedadnombrecientifico, enfermedadtipo, enfermedaddescripcion, enfermedadsintomas, enfermedadtratamiento, enfermedadcausas, enfermedadprevenciones, enfermedadfoto, temporada, severidad)VALUES('Oídio', 'Erysiphe necator', 'HONGO', 'Polvo blanco sobre hojas y tallos.', 'Manchas blancas y marchitez.', 'Fungicidas a base de azufre.', 'Alta humedad y poca luz.', 'Buena ventilación y poda.', 'https://example.com/img/oidio.jpg', 'VERANO', 'MODERADA'),('Botritis', 'Botrytis cinerea', 'HONGO', 'Moho gris que afecta flores.', 'Manchas marrones con esporas grises.', 'Fungicidas y eliminación de tejidos enfermos.', 'Ambientes húmedos y fríos.', 'Riego moderado y ventilación.', 'https://example.com/img/botritis.jpg', 'OTOÑO', 'GRAVE'),('Mancha bacteriana', 'Xanthomonas campestris', 'BACTERIA', 'Provoca lesiones marrones rodeadas de halo amarillo.', 'Manchas circulares y necrosis.', 'Bactericidas de cobre.', 'Alta humedad.', 'Semillas certificadas y rotación.', 'https://example.com/img/mancha_bacteriana.jpg', 'PRIMAVERA', 'MODERADA');

-- ===== BLOGS =====
INSERT INTO blog (usuarioid, blogtipo, blogtitulo, blogdescripcion, blogimagen, blogestado, blogfechapublicacion)VALUES(2, 'NEWS', 'Avances en control biológico de plagas', 'Nuevas especies depredadoras introducidas con éxito en cultivos de tomate.', 'https://example.com/img/blog/biocontrol.jpg', 'ACTIVO', NOW()),(3, 'TIP', 'Cómo prevenir el oídio en invierno', 'Consejos prácticos para mantener tus cultivos libres de hongos durante el invierno.', 'https://example.com/img/blog/oidio.jpg', 'ACTIVO', NOW());

-- ===== POSTS =====
INSERT INTO post (usuarioid, posttitulo, postdescripcion, postfoto, postfecha)VALUES(2, 'Mi cultivo de tomates con pulgones', 'Encontré muchos pulgones esta semana, ¿alguna recomendación natural?', 'https://example.com/img/posts/pulgones.jpg', NOW()),(3, 'Control de oídio sin químicos', 'Probé un fungicida natural y funcionó excelente.', 'https://example.com/img/posts/oidio_control.jpg', NOW());

-- ===== COMENTARIOS =====
INSERT INTO comentario (postid, usuarioid, comentariotexto, comentariofecha)VALUES(1, 3, 'Puedes probar con jabón potásico, me ayudó mucho.', NOW()),(1, 4, 'También sirve agua con ajo y chile.', NOW()),(2, 2, 'Muy buen consejo, gracias por compartir.', NOW());

-- ===== FEEDBACK =====
INSERT INTO feedback (usuarioid, feedbacktipo, feedbackdescripcion, feedbackrating, feedbackfecha)VALUES(2, 'GENERAL', 'Excelente experiencia, muy útil la detección automática.', 5, NOW()),(3, 'APP_PROBLEM', 'La cámara se congela al subir una foto grande.', 3, NOW()),(4, 'SUGGESTION', 'Sería genial tener un modo offline.', 4, NOW());



SELECT setval(pg_get_serial_sequence('rol', 'rolid'), COALESCE(MAX(rolid), 0) + 1, false) FROM rol;
SELECT setval(pg_get_serial_sequence('usuario', 'usuarioid'), COALESCE(MAX(usuarioid), 0) + 1, false) FROM usuario;
SELECT setval(pg_get_serial_sequence('plaga', 'plagaid'), COALESCE(MAX(plagaid), 0) + 1, false) FROM plaga;
SELECT setval(pg_get_serial_sequence('enfermedad', 'enfermedadid'), COALESCE(MAX(enfermedadid), 0) + 1, false) FROM enfermedad;
SELECT setval(pg_get_serial_sequence('blog', 'blogid'), COALESCE(MAX(blogid), 0) + 1, false) FROM blog;
SELECT setval(pg_get_serial_sequence('post', 'postid'), COALESCE(MAX(postid), 0) + 1, false) FROM post;
SELECT setval(pg_get_serial_sequence('comentario', 'comentarioid'), COALESCE(MAX(comentarioid), 0) + 1, false) FROM comentario;
SELECT setval(pg_get_serial_sequence('feedback', 'feedbackid'), COALESCE(MAX(feedbackid), 0) + 1, false) FROM feedback;
