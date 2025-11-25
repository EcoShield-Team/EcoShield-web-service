-- ===== ROLES =====
INSERT INTO rol (rolnombre, roldescripcion) VALUES ('ADMIN', 'Rol por defecto para administradores');
INSERT INTO rol (rolnombre, roldescripcion) VALUES ('USER', 'Rol por defecto de usuarios');


-- ========================
-- 👤 TABLA: USUARIO                 contraseña: password / admin123
-- ========================
INSERT INTO usuario (usuarioid, rolid, usuarionombre, usuariocorreo, usuariocontrasena, usuarioestado, usuariofotoperfil, usuariopais, usuariofecharegistro)
VALUES
    (1, 1, 'Gerardo Chávez', 'gerardomanuelrichard@gmail.com', '$2a$12$0eZ.w9rAEsY9bzl00.uLte4mE5.agTRH7T1qvrRE54BqNDVk6EE1K', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996306/profile1_ctpzyg.png', 'Perú', CURRENT_TIMESTAMP),
    (2, 1, 'Admin Global', 'admin@ecoshield.com', '$2a$12$zUO15GSNu.N6LrphgchVCeVAGsoXYI/wx9c896S7uxppJVeoqMckG', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996306/profile11_xrllds.jpg', 'Argentina', CURRENT_TIMESTAMP),
    (3, 2, 'Alexander Aquino', 'alexander@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996306/profile2_hrkoiy.jpg', 'Perú', CURRENT_TIMESTAMP),
    (4, 2, 'Camilo Parraga', 'camilopp810@gmail.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996306/profile3_uj5yw6.jpg', 'Ecuador', CURRENT_TIMESTAMP),
    (5, 2, 'Mauricio Mantilla', 'mauricio@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996306/profile10_oks6ue.avif', 'Colombia', CURRENT_TIMESTAMP),
    (6, 2, 'Marcelo Rotta', 'marcelo@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile4_ojix5z.jpg', 'Bolivia', CURRENT_TIMESTAMP),
    (7, 2, 'Hadisha Ludeña', 'hadisha@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile12_vusbwz.jpg', 'Perú', CURRENT_TIMESTAMP),
    (8, 2, 'Diego Castro', 'diego@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'BLOQUEADO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile7_mpxppf.jpg', 'Chile', CURRENT_TIMESTAMP),
    (9, 2, 'Laura Llanos', 'laura@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile6_olmnjd.jpg', 'México', CURRENT_TIMESTAMP),
    (10, 2, 'César Rodríguez', 'cesar@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile5_c41umt.jpg', 'Perú', CURRENT_TIMESTAMP),
    (11, 2, 'Gonzalo Morales', 'gonzalo@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996307/profile8_bfc0ao.jpg', 'Colombia', CURRENT_TIMESTAMP),
    (12, 2, 'Andrea Paredes', 'andrea@ecoshield.com', '$2a$12$osIgA6Ts4o/eKucuIP9wpuZmvAqEslWEuvkDSehCZOx259.t9I6pC', 'ACTIVO', 'https://res.cloudinary.com/dsm9krdik/image/upload/v1759996311/profile9_aibspy.jpg', 'Perú', CURRENT_TIMESTAMP);


-- ========================
-- 🪲 TABLA: PLAGA
-- ========================
INSERT INTO plaga (plagaid, plaganombre, plaganombrecientifico, plagatipo, plagadescripcion, plagasintomas, plagatratamiento, plagacausas, plagaprevenciones, plagafoto, temporada, severidad)
VALUES
    (1, 'Pulgón verde del duraznero', 'Myzus persicae', 'INSECTO',
     'Insecto chupador que se alimenta de la savia de las hojas tiernas.',
     'Hojas enrolladas y amarillentas, presencia de melaza y hormigas.',
     'Aplicar jabón potásico o extracto de neem.',
     'Temperaturas templadas y exceso de nitrógeno.',
     'Podar brotes infestados y fomentar enemigos naturales.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga1_dmtlo3.jpg', 'PRIMAVERA', 'LEVE'),

    (2, 'Mosca blanca', 'Bemisia tabaci', 'INSECTO',
     'Insecto que debilita la planta al alimentarse de su savia.',
     'Hojas amarillas, caída prematura y melaza pegajosa.',
     'Uso de trampas cromáticas amarillas y liberación de Encarsia formosa.',
     'Climas cálidos y secos.',
     'Evitar exceso de abono y controlar malezas.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga2_vpyz7k.jpg', 'VERANO', 'MODERADA'),

    (3, 'Ácaro rojo', 'Tetranychus urticae', 'ACARO',
     'Ácaro diminuto que causa decoloración y necrosis en hojas.',
     'Manchas amarillas, telarañas finas en el envés.',
     'Azufre micronizado o extractos de ajo.',
     'Alta temperatura y baja humedad.',
     'Mantener humedad adecuada y revisar el envés de hojas.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998737/plaga3_ta4wyh.jpg', 'VERANO', 'GRAVE'),

    (4, 'Gusano cogollero', 'Spodoptera frugiperda', 'INSECTO',
     'Oruga que ataca el cogollo de maíz y otros cultivos.',
     'Hojas perforadas y cogollos destruidos.',
     'Aplicar Bacillus thuringiensis o control biológico.',
     'Alta humedad y monocultivo.',
     'Rotación de cultivos y trampas de feromonas.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998734/plaga4_oicvmz.jpg', 'TODO_EL_AÑO', 'GRAVE'),

    (5, 'Nematodo de las raíces', 'Meloidogyne spp.', 'NEMATODO',
     'Parásito microscópico que deforma raíces y reduce crecimiento.',
     'Nódulos o agallas en raíces.',
     'Solarización y uso de extractos de neem.',
     'Suelos infectados mal drenados.',
     'Usar semillas certificadas y rotar cultivos.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998734/plaga5_uq3k15.jpg', 'VERANO', 'MODERADA'),

    (6, 'Trips del tomate', 'Frankliniella occidentalis', 'INSECTO',
     'Insecto que succiona el contenido celular de hojas y flores.',
     'Deformaciones, manchas plateadas, flores caídas.',
     'Aplicar jabón potásico y mantener humedad.',
     'Alta densidad de siembra y calor.',
     'Desinfectar herramientas y eliminar restos vegetales.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga6_kvrq7k.jpg', 'PRIMAVERA', 'LEVE'),

    (7, 'Minador de hojas', 'Liriomyza trifolii', 'INSECTO',
     'Larvas que excavan galerías en el interior de las hojas.',
     'Líneas serpenteantes en hojas.',
     'Poda de hojas afectadas y trampas pegajosas.',
     'Alta temperatura y exceso de riego.',
     'Rotación de cultivos y control biológico.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga7_vi3xqb.jpg', 'OTOÑO', 'LEVE'),

    (8, 'Cochinilla algodonosa', 'Planococcus citri', 'INSECTO',
     'Insecto cubierto de cera blanca que afecta cítricos y ornamentales.',
     'Pérdida de vigor, hojas deformadas y melaza.',
     'Control biológico con Cryptolaemus montrouzieri.',
     'Climas cálidos y secos.',
     'Mantener ventilación y limpieza del cultivo.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga8_bu7mtx.jpg', 'TODO_EL_AÑO', 'MODERADA'),

    (9, 'Araña amarilla', 'Polyphagotarsonemus latus', 'ACARO',
     'Causa deformación en hojas y brotes.',
     'Hojas enrolladas y clorosis.',
     'Aplicar extracto de ajo o jabón insecticida.',
     'Altas temperaturas y baja humedad.',
     'Controlar riego y aplicar biocontroladores.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998737/plaga9_ot5ian.jpg', 'VERANO', 'LEVE'),

    (10, 'Escarabajo del pepino', 'Diabrotica balteata', 'INSECTO',
     'Se alimenta de hojas y transmite virus.',
     'Agujeros en hojas y tallos.',
     'Control con trampas y Beauveria bassiana.',
     'Alta temperatura y malezas cercanas.',
     'Rotar cultivos y eliminar residuos.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998735/plaga10_g87yzj.jpg', 'PRIMAVERA', 'MODERADA');

-- 🦟 1. Pulgón verde del duraznero
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (1, 'pulgones verdes'),
                                              (1, 'pulgón del melocotonero'),
                                              (1, 'pulgón del durazno'),
                                              (1, 'pulgón común'),
                                              (1, 'green peach aphid'),
                                              (1, 'aphid myzus persicae'),
                                              (1, 'greenfly'),
                                              (1, 'peach aphid'),
                                              (1, 'áfido verde'),
                                              (1, 'insecto chupador'),
                                              (1, 'plaga de hojas tiernas'),
                                              (1, 'myzus persicae'),
                                              (1, 'pulgón agrícola'),
                                              (1, 'áfido del pimiento'),
                                              (1, 'áfido del tomate');

-- 🪰 2. Mosca blanca
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (2, 'mosquita blanca'),
                                              (2, 'whitefly'),
                                              (2, 'bemisia'),
                                              (2, 'aleurodidae'),
                                              (2, 'mosca blanca del tomate'),
                                              (2, 'aleurodes vaporariorum'),
                                              (2, 'greenhouse whitefly'),
                                              (2, 'mosca blanca de los invernaderos'),
                                              (2, 'whiteflies'),
                                              (2, 'plaga chupadora'),
                                              (2, 'mosca blanca común'),
                                              (2, 'mosca blanca del pepino'),
                                              (2, 'mosca blanca del frijol'),
                                              (2, 'pequeña mosca blanca'),
                                              (2, 'mosca blanca tropical');

-- 🕷️ 3. Ácaro rojo
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (3, 'spider mite'),
                                              (3, 'red spider mite'),
                                              (3, 'ácaro del tomate'),
                                              (3, 'ácaro de dos manchas'),
                                              (3, 'ácaro rojo común'),
                                              (3, 'tetranychus'),
                                              (3, 'tetranychus urticae'),
                                              (3, 'mite pest'),
                                              (3, 'plaga arácnida'),
                                              (3, 'ácaros fitófagos'),
                                              (3, 'ácaro telarañero'),
                                              (3, 'spotted spider mite'),
                                              (3, 'ácaro rojo de las hojas'),
                                              (3, 'two-spotted spider mite'),
                                              (3, 'plaga del algodón');

-- 🐛 4. Gusano cogollero
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (4, 'fall armyworm'),
                                              (4, 'spodoptera frugiperda'),
                                              (4, 'gusano del maíz'),
                                              (4, 'oruga cogollera'),
                                              (4, 'cogollero del maíz'),
                                              (4, 'armyworm'),
                                              (4, 'gusano del sorgo'),
                                              (4, 'gusano de las hojas'),
                                              (4, 'spodoptera'),
                                              (4, 'cogollero tropical'),
                                              (4, 'gusano barrenador'),
                                              (4, 'gusano devorador de hojas'),
                                              (4, 'larva defoliadora'),
                                              (4, 'maize armyworm'),
                                              (4, 'oruga del maíz');

-- 🧬 5. Nematodo de las raíces
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (5, 'root-knot nematode'),
                                              (5, 'nematodo agallador'),
                                              (5, 'nematodo del suelo'),
                                              (5, 'meloidogyne'),
                                              (5, 'nemátodos agalladores'),
                                              (5, 'root nematode'),
                                              (5, 'nematodo de las plantas'),
                                              (5, 'nemátodo parásito'),
                                              (5, 'nematodo radicular'),
                                              (5, 'nemátodo dañino'),
                                              (5, 'plaga subterránea'),
                                              (5, 'gall nematode'),
                                              (5, 'root knot'),
                                              (5, 'nemátodo de raíces'),
                                              (5, 'meloidogyne incognita');

-- 🌸 6. Trips del tomate
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (6, 'thrips'),
                                              (6, 'frankliniella occidentalis'),
                                              (6, 'trips de las flores'),
                                              (6, 'western flower thrips'),
                                              (6, 'pequeños trips'),
                                              (6, 'insecto trips'),
                                              (6, 'plaga de flores'),
                                              (6, 'trips del invernadero'),
                                              (6, 'flower thrips'),
                                              (6, 'tomato thrips'),
                                              (6, 'frankliniella'),
                                              (6, 'trips de los pétalos'),
                                              (6, 'trips común'),
                                              (6, 'trips amarillo'),
                                              (6, 'trips de hojas');

-- 🍃 7. Minador de hojas
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (7, 'leaf miner'),
                                              (7, 'minador'),
                                              (7, 'minador americano'),
                                              (7, 'liriomyza'),
                                              (7, 'minador de hojas de tomate'),
                                              (7, 'liriomyza trifolii'),
                                              (7, 'minador del frijol'),
                                              (7, 'minador del melón'),
                                              (7, 'plaga de hojas'),
                                              (7, 'mining fly'),
                                              (7, 'hoja minada'),
                                              (7, 'larva minadora'),
                                              (7, 'minador común'),
                                              (7, 'leaf-mining fly'),
                                              (7, 'minador vegetal');

-- 🍊 8. Cochinilla algodonosa
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (8, 'cochinilla blanca'),
                                              (8, 'cochinilla algodonosa'),
                                              (8, 'planococcus citri'),
                                              (8, 'mealybug'),
                                              (8, 'cochinilla de los cítricos'),
                                              (8, 'citrus mealybug'),
                                              (8, 'plaga cerosa'),
                                              (8, 'cochinilla harinosa'),
                                              (8, 'insecto algodonoso'),
                                              (8, 'cochinilla del mango'),
                                              (8, 'white mealybug'),
                                              (8, 'plaga del limón'),
                                              (8, 'mealy insect'),
                                              (8, 'plaga algodonosa'),
                                              (8, 'cochinilla adherida');

-- 🕸️ 9. Araña amarilla
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (9, 'broad mite'),
                                              (9, 'ácaro amarillo'),
                                              (9, 'tarsonémido'),
                                              (9, 'yellow mite'),
                                              (9, 'polyphagotarsonemus latus'),
                                              (9, 'ácaro ancho'),
                                              (9, 'ácaro del chile'),
                                              (9, 'broad spider mite'),
                                              (9, 'ácaro de la papaya'),
                                              (9, 'mite amarillo'),
                                              (9, 'plaga microscópica'),
                                              (9, 'ácaro de hoja'),
                                              (9, 'ácaro de hortalizas'),
                                              (9, 'yellow broad mite'),
                                              (9, 'tarsonemid mite');

-- 🪲 10. Escarabajo del pepino
INSERT INTO plaga_alias (plaga_id, alias) VALUES
                                              (10, 'cucumber beetle'),
                                              (10, 'diabrotica'),
                                              (10, 'escarabajo rayado del pepino'),
                                              (10, 'beetle pest cucumber'),
                                              (10, 'escarabajo del pepino'),
                                              (10, 'diabrotica undecimpunctata'),
                                              (10, 'striped cucumber beetle'),
                                              (10, 'spotted cucumber beetle'),
                                              (10, 'escarabajo amarillo y negro'),
                                              (10, 'beetle plaga de pepino'),
                                              (10, 'diabrotica balteata'),
                                              (10, 'escarabajo de las cucurbitáceas'),
                                              (10, 'cucumber pest'),
                                              (10, 'plaga del pepino'),
                                              (10, 'beetle larvae cucumber');
-- ========================
-- 🌱 TABLA: ENFERMEDAD
-- ========================
INSERT INTO enfermedad (enfermedadid, enfermedadnombre, enfermedadnombrecientifico, enfermedadtipo, enfermedaddescripcion, enfermedadsintomas, enfermedadtratamiento, enfermedadcausas, enfermedadprevenciones, enfermedadfoto, temporada, severidad)
VALUES
    (1, 'Tizón tardío del tomate', 'Phytophthora infestans', 'HONGO',
     'Afecta hojas, tallos y frutos causando necrosis.',
     'Manchas oscuras y moho gris.',
     'Aplicar cobre o Trichoderma harzianum.',
     'Alta humedad y bajas temperaturas.',
     'Rotar cultivos y eliminar restos infectados.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999177/enf1_mbcgfd.jpg', 'INVIERNO', 'GRAVE'),

    (2, 'Roya del café', 'Hemileia vastatrix', 'HONGO',
     'Hongo que afecta las hojas del cafeto causando pérdidas severas.',
     'Manchas amarillas en el envés.',
     'Fungicidas naturales o biocontrol.',
     'Lluvia y humedad constante.',
     'Podas y variedades resistentes.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf2_ukmwv0.jpg', 'OTOÑO', 'GRAVE'),

    (3, 'Marchitez bacteriana del plátano', 'Ralstonia solanacearum', 'BACTERIA',
     'Provoca marchitez general de la planta.',
     'Hojas caídas y tallo ennegrecido.',
     'Desinfección del suelo y control biológico.',
     'Alta temperatura y suelo infectado.',
     'Rotación y material libre de patógenos.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf3_qrtnvz.jpg', 'VERANO', 'GRAVE'),

    (4, 'Virus del mosaico del pepino', 'Cucumber mosaic virus', 'VIRUS',
     'Virus transmitido por pulgones que afecta hortalizas.',
     'Mosaicos amarillos en hojas.',
     'Eliminar plantas afectadas.',
     'Presencia de vectores.',
     'Uso de semillas certificadas.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf4_vgrwsp.jpg', 'PRIMAVERA', 'MODERADA'),

    (5, 'Mancha negra de la rosa', 'Diplocarpon rosae', 'HONGO',
     'Causa defoliación prematura en rosales.',
     'Manchas negras en hojas.',
     'Poda y tratamiento con cobre.',
     'Exceso de humedad.',
     'Mejorar aireación y riego controlado.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf5_wv1uvr.jpg', 'PRIMAVERA', 'LEVE'),

    (6, 'Pudrición de raíz', 'Fusarium oxysporum', 'HONGO',
     'Destruye el sistema radicular y causa marchitez.',
     'Amarillamiento y marchitez.',
     'Aplicar Trichoderma y mejorar drenaje.',
     'Suelos mal drenados.',
     'Evitar riegos excesivos.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf6_vetcgi.jpg', 'TODO_EL_AÑO', 'GRAVE'),

    (7, 'Agalla bacteriana', 'Agrobacterium tumefaciens', 'BACTERIA',
     'Genera tumores en raíces y tallos.',
     'Protuberancias en cuello de raíz.',
     'Desinfectar herramientas.',
     'Contaminación cruzada.',
     'Rotación y control sanitario.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999179/enf7_vxruvk.jpg', 'VERANO', 'LEVE'),

    (8, 'Mildiu polvoriento', 'Erysiphe cichoracearum', 'HONGO',
     'Polvo blanco sobre hojas y tallos.',
     'Deformación foliar y caída prematura.',
     'Azufre micronizado.',
     'Alta humedad y poca luz.',
     'Evitar hacinamiento.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999178/enf8_biqrrx.jpg', 'PRIMAVERA', 'MODERADA'),

    (9, 'Virus del enrollamiento de la hoja del tomate', 'Tomato leaf curl virus', 'VIRUS',
     'Transmisión por mosca blanca en climas cálidos.',
     'Hojas rizadas y crecimiento atrofiado.',
     'Eliminar plantas infectadas.',
     'Vector no controlado.',
     'Control de insectos.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999179/enf9_vd22gh.png', 'VERANO', 'MODERADA'),

    (10, 'Nematodo de la raíz del café', 'Pratylenchus coffeae', 'NEMATODO',
     'Daña raíces de café y otras plantas causando pérdida de vigor.',
     'Raíces ennegrecidas y necrosadas.',
     'Solarización del suelo.',
     'Suelo contaminado.',
     'Rotación y control biológico.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759999179/enf10_taos3y.jpg', 'OTOÑO', 'LEVE');

-- 1️⃣ Tizón tardío del tomate
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (1, 'late blight'),
                                                        (1, 'tizón tardío'),
                                                        (1, 'tizon tardio'),
                                                        (1, 'tizón de la papa'),
                                                        (1, 'tizón del tomate'),
                                                        (1, 'phytophthora infestans'),
                                                        (1, 'blight tomato disease'),
                                                        (1, 'tomato late blight'),
                                                        (1, 'potato blight'),
                                                        (1, 'tizón fungoso'),
                                                        (1, 'enfermedad del tizón'),
                                                        (1, 'tizón húmedo'),
                                                        (1, 'tomato fungus blight'),
                                                        (1, 'hongos del tomate'),
                                                        (1, 'leaf blight');

-- 2️⃣ Roya del café
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (2, 'coffee rust'),
                                                        (2, 'roya del cafeto'),
                                                        (2, 'roya del café'),
                                                        (2, 'hemileia vastatrix'),
                                                        (2, 'leaf rust coffee'),
                                                        (2, 'coffee leaf rust'),
                                                        (2, 'coffee fungus'),
                                                        (2, 'roya anaranjada'),
                                                        (2, 'rust coffee leaves'),
                                                        (2, 'hongos del café'),
                                                        (2, 'rust fungus'),
                                                        (2, 'roya amarilla'),
                                                        (2, 'hemileia'),
                                                        (2, 'orange leaf rust'),
                                                        (2, 'coffee disease rust');

-- 3️⃣ Marchitez bacteriana del plátano
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (3, 'bacterial wilt'),
                                                        (3, 'moko disease'),
                                                        (3, 'marchitez bacteriana'),
                                                        (3, 'marchitez del plátano'),
                                                        (3, 'marchitez vascular del platano'),
                                                        (3, 'ralstonia solanacearum'),
                                                        (3, 'banana wilt'),
                                                        (3, 'banana moko'),
                                                        (3, 'bacterial banana wilt'),
                                                        (3, 'vascular wilt banana'),
                                                        (3, 'enfermedad del moko'),
                                                        (3, 'plátano enfermo'),
                                                        (3, 'wilt banana disease'),
                                                        (3, 'plátano marchito'),
                                                        (3, 'xanthomonas banana wilt');

-- 4️⃣ Virus del mosaico del pepino
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (4, 'cucumber mosaic'),
                                                        (4, 'mosaico del pepino'),
                                                        (4, 'virus del mosaico del pepino'),
                                                        (4, 'cmv virus'),
                                                        (4, 'virus del mosaico del pepinillo'),
                                                        (4, 'cucumber mosaic virus infection'),
                                                        (4, 'pepino con mosaico'),
                                                        (4, 'mosaico vegetal'),
                                                        (4, 'mosaico de hojas'),
                                                        (4, 'virus del mosaico vegetal'),
                                                        (4, 'virus cmv'),
                                                        (4, 'cucumber leaf mosaic'),
                                                        (4, 'mosaic leaf disease'),
                                                        (4, 'mosaico cucurbitáceas'),
                                                        (4, 'virus de hojas manchadas');

-- 5️⃣ Mancha negra de la rosa
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (5, 'black spot rose'),
                                                        (5, 'black spot disease'),
                                                        (5, 'mancha negra de la rosa'),
                                                        (5, 'mancha negra en rosales'),
                                                        (5, 'diplocarpon rosae'),
                                                        (5, 'blackspot fungus'),
                                                        (5, 'hongos de rosa'),
                                                        (5, 'leaf spot rose'),
                                                        (5, 'black spot fungus'),
                                                        (5, 'manchas en pétalos'),
                                                        (5, 'black spots on rose leaves'),
                                                        (5, 'rose leaf black spot'),
                                                        (5, 'rosa enferma'),
                                                        (5, 'black spot infection'),
                                                        (5, 'leaf spot disease');

-- 6️⃣ Pudrición de raíz
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (6, 'root rot'),
                                                        (6, 'fusarium wilt'),
                                                        (6, 'fusarium oxysporum'),
                                                        (6, 'pudrición radicular'),
                                                        (6, 'root decay'),
                                                        (6, 'root fungus'),
                                                        (6, 'rhizoctonia'),
                                                        (6, 'pudricion de la raíz'),
                                                        (6, 'root rot disease'),
                                                        (6, 'plant root rot'),
                                                        (6, 'hongo del suelo'),
                                                        (6, 'soil fungus rot'),
                                                        (6, 'root infection'),
                                                        (6, 'fungal rot'),
                                                        (6, 'pudrición del tallo');

-- 7️⃣ Agalla bacteriana
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (7, 'crown gall'),
                                                        (7, 'agalla bacteriana'),
                                                        (7, 'agalla de la raíz'),
                                                        (7, 'agrobacterium'),
                                                        (7, 'agrobacterium tumefaciens'),
                                                        (7, 'tumor bacteriano'),
                                                        (7, 'bacterial gall'),
                                                        (7, 'bacterial crown gall'),
                                                        (7, 'tumor vegetal'),
                                                        (7, 'root crown gall'),
                                                        (7, 'planta con agallas'),
                                                        (7, 'crown gall disease'),
                                                        (7, 'gall tumor plant'),
                                                        (7, 'bacterial plant tumor'),
                                                        (7, 'root gall infection');

-- 8️⃣ Mildiu polvoriento
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (8, 'powdery mildew'),
                                                        (8, 'mildiu polvoriento'),
                                                        (8, 'mildiu blanco'),
                                                        (8, 'oidio'),
                                                        (8, 'erysiphe'),
                                                        (8, 'mildiu en hojas'),
                                                        (8, 'leaf mildew'),
                                                        (8, 'white mildew'),
                                                        (8, 'powder mildew'),
                                                        (8, 'oidio de hojas'),
                                                        (8, 'mildiu superficial'),
                                                        (8, 'hongos blancos'),
                                                        (8, 'mildiu de tallo'),
                                                        (8, 'blanca en hojas'),
                                                        (8, 'mildew fungus');

-- 9️⃣ Virus del enrollamiento de la hoja del tomate
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (9, 'tomato leaf curl'),
                                                        (9, 'tlcv'),
                                                        (9, 'leaf curl virus'),
                                                        (9, 'virus del rizado de hojas de tomate'),
                                                        (9, 'tomato curl'),
                                                        (9, 'virus de enrollamiento'),
                                                        (9, 'tomato yellow leaf curl'),
                                                        (9, 'leaf curling virus'),
                                                        (9, 'rizado de hoja'),
                                                        (9, 'hojas arrugadas tomate'),
                                                        (9, 'virus de hojas amarillas'),
                                                        (9, 'tomato leaf twist'),
                                                        (9, 'virus del tomate amarillo'),
                                                        (9, 'leaf curl disease'),
                                                        (9, 'tomato leaf roll');

-- 🔟 Nematodo de la raíz del café
INSERT INTO enfermedad_alias (enfermedad_id, alias) VALUES
                                                        (10, 'root lesion nematode'),
                                                        (10, 'pratylenchus'),
                                                        (10, 'pratylenchus coffeae'),
                                                        (10, 'nematodo del café'),
                                                        (10, 'root nematode coffee'),
                                                        (10, 'nemátodo parásito'),
                                                        (10, 'root lesion'),
                                                        (10, 'nematodo de raíces finas'),
                                                        (10, 'coffee nematode'),
                                                        (10, 'root-knot nematode coffee'),
                                                        (10, 'plaga de raíces del café'),
                                                        (10, 'root pest coffee'),
                                                        (10, 'nemátodo del cafeto'),
                                                        (10, 'root disease coffee'),
                                                        (10, 'nematode infection coffee');
-- ===========================================
-- 📰 TABLA: BLOG
-- ===========================================

INSERT INTO blog (blogid, usuarioid, blogtipo, blogtitulo, blogdescripcion, blogimagen, blogestado, blogfechapublicacion)
VALUES
    (1, 1, 'NEWS', 'EcoShield lanza su nueva IA para detección de plagas',
     'La última actualización de EcoShield integra un modelo de inteligencia artificial capaz de identificar plagas y enfermedades agrícolas con un 95% de precisión. Este avance busca optimizar los recursos del agricultor y reducir el uso de pesticidas químicos.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog1_ku9zsf.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (2, 1, 'TIP', '5 prácticas sostenibles para proteger tus cultivos',
     'Adoptar prácticas agrícolas sostenibles como el riego por goteo, la rotación de cultivos y el uso de biopesticidas no solo mejora el rendimiento, sino que protege la biodiversidad del suelo. Descubre cómo aplicarlas en tu parcela.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog2_eyalkf.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (3, 2, 'NEWS', 'Nuevo acuerdo con instituciones agrícolas latinoamericanas',
     'EcoShield ha firmado un convenio con instituciones agrícolas de Perú, Colombia y México para promover el uso de herramientas digitales en el monitoreo de plagas. Esta alianza permitirá ampliar la base de datos de detecciones en toda la región.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997491/blog3_yequj6.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (4, 2, 'TIP', 'Cómo identificar señales tempranas de enfermedades en tus plantas',
     'Observar el color de las hojas, el brillo de los tallos y la presencia de manchas o deformaciones puede ayudarte a detectar enfermedades antes de que se propaguen. Conoce los principales indicadores que EcoShield analiza mediante IA.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997489/blog4_vojbxj.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (5, 1, 'TIP', 'Ahorra agua sin afectar la salud de tus cultivos',
     'El uso de sensores de humedad y sistemas de riego inteligente permite reducir hasta un 40% el consumo de agua. Aprende cómo configurar alertas automáticas desde tu cuenta EcoShield para evitar el exceso de riego.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog5_a32d8y.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (6, 2, 'NEWS', 'EcoShield alcanza 10,000 usuarios activos en Latinoamérica',
     'Gracias a la comunidad agrícola y urbana, EcoShield supera los 10,000 usuarios activos en la región. El crecimiento demuestra el interés en soluciones tecnológicas sostenibles para el manejo de cultivos.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997489/blog6_bfymep.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (7, 1, 'TIP', 'Guía rápida para interpretar tus resultados de detección',
     'Cuando EcoShield identifica una plaga o enfermedad, el sistema genera un informe detallado con el nivel de confianza, el tipo de daño y las acciones sugeridas. Aprende cómo leer estos datos para tomar decisiones precisas.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog7_cehkup.png',
     'ACTIVO', CURRENT_TIMESTAMP),

    (8, 1, 'NEWS', 'Implementamos nuevos filtros de búsqueda en el catálogo de plagas',
     'Ahora los usuarios pueden filtrar plagas y enfermedades por tipo, severidad y temporada. Esto mejora la experiencia de búsqueda y facilita el aprendizaje sobre los cultivos más comunes en su región.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog8_jz1ebd.png',
     'ACTIVO', CURRENT_TIMESTAMP),

    (9, 2, 'TIP', 'Cómo mantener tu huerto libre de plagas sin químicos',
     'Utiliza trampas cromáticas, cultivos asociados y biopesticidas naturales para reducir la aparición de plagas. Estas alternativas ecológicas son seguras y efectivas para pequeños huertos urbanos.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog9_x9j7jz.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (10, 2, 'NEWS', 'EcoShield presenta su módulo de análisis climático en tiempo real',
     'Con esta nueva función, los agricultores podrán recibir alertas meteorológicas personalizadas que anticipan condiciones favorables para la aparición de plagas o enfermedades.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997490/blog10_xoazwn.jpg',
     'ACTIVO', CURRENT_TIMESTAMP),

    (11, 1, 'TIP', 'Cómo almacenar correctamente tus productos biológicos',
     'Muchos productos naturales pierden efectividad si se exponen a la luz solar o humedad. Aprende a conservar biopesticidas y biofertilizantes en condiciones óptimas para su uso prolongado.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997491/blog11_vsjumk.jpg',
     'INACTIVO', CURRENT_TIMESTAMP),

    (12, 2, 'NEWS', 'Actualización del ecosistema EcoShield 1.0',
     'La nueva versión de la plataforma mejora la velocidad de análisis y añade soporte para imágenes de alta resolución, optimizando la precisión de detección y la gestión de datos del usuario.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759997491/blog12_tje8ro.png',
     'ARCHIVADO', CURRENT_TIMESTAMP);

-- ========================
-- 📸 TABLA: POST
-- ========================
INSERT INTO post (postid, usuarioid, posttitulo, postdescripcion, postfoto, postfecha)
VALUES
    (1, 2, 'Problemas con hongos en hojas de tomate',
     'Mis plantas de tomate muestran manchas oscuras y secas en las hojas inferiores. Alguien sabe si esto es tizón o alguna otra enfermedad?',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998075/post1_xibc3f.jpg',
     '2025-09-28 14:32:10'),

    (2, 4, 'Plaga en hojas de lechuga — ayuda urgente',
     'Encontré pequeños insectos blancos en el envés de mis hojas de lechuga. ¿Podría tratarse de mosca blanca? ¿Qué puedo hacer?',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998076/post2_z37ybc.jpg',
     '2025-10-03 09:17:54'),

    (3, 6, 'Resultados con biopesticidas naturales',
     'He estado probando extracto de ajo y neem para controlar pulgones, y los resultados han sido buenos. Les comparto mi experiencia.',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998075/post3_xh9rmz.jpg',
     '2025-10-11 19:45:22'),

    (4, 9, 'Recomendaciones para cultivar fresas en clima seco',
     'Vivo en una zona con poca humedad y me cuesta mantener las fresas saludables. ¿Qué consejos pueden darme para evitar plagas y deshidratación?',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998076/post4_xpjkdi.jpg',
     '2025-10-19 07:58:43'),

    (5, 10, 'Nuevo usuario de EcoShield — mi primera detección',
     'Probé el sistema de detección de plagas y me funcionó muy bien. Detectó una posible infestación de trips en mis pimientos. ¡Recomendado!',
     'https://res.cloudinary.com/dsm9krdik/image/upload/v1759998076/post5_lpoyd9.png',
     '2025-11-02 16:24:11');


-- ========================
-- 💬 TABLA: COMENTARIO
-- ========================
INSERT INTO comentario (comentarioid, postid, usuarioid, comentariotexto, comentariofecha)
VALUES
-- Comentarios del Post 1
(1, 1, 3, 'Sí, parece tizón tardío. Puedes confirmarlo subiendo una foto más cercana.', CURRENT_TIMESTAMP),
(2, 1, 8, 'Si es tizón, evita mojar las hojas al regar y usa Trichoderma.', CURRENT_TIMESTAMP),
(3, 1, 1, 'Buen aporte. También puedes revisar el nivel de humedad del sustrato.', CURRENT_TIMESTAMP),

-- Comentarios del Post 2
(4, 2, 5, 'Por la descripción, suena a mosca blanca. Usa trampas amarillas.', CURRENT_TIMESTAMP),
(5, 2, 12, 'Yo tuve el mismo problema, el jabón potásico ayudó mucho.', CURRENT_TIMESTAMP),
(6, 2, 1, 'Correcto, es mosca blanca. Controla la ventilación y elimina hojas afectadas.', CURRENT_TIMESTAMP),

-- Comentarios del Post 3
(7, 3, 2, 'Excelente, el neem es muy efectivo contra pulgones y cochinillas.', CURRENT_TIMESTAMP),
(8, 3, 11, 'Gracias por compartir, probaré esa mezcla con mis plantas ornamentales.', CURRENT_TIMESTAMP),
(9, 3, 8, 'Recuerda no aplicar al mediodía, puede quemar las hojas.', CURRENT_TIMESTAMP),

-- Comentarios del Post 4
(10, 4, 6, 'En climas secos puedes usar mulch para mantener la humedad.', CURRENT_TIMESTAMP),
(11, 4, 10, 'Riega temprano y usa cobertura vegetal. Me ha funcionado bien.', CURRENT_TIMESTAMP),

-- Comentarios del Post 5
(12, 5, 4, '¡Genial! Yo también tuve detecciones precisas en tomates.', CURRENT_TIMESTAMP),
(13, 5, 9, 'El modelo ha mejorado mucho en las últimas versiones.', CURRENT_TIMESTAMP),
(14, 5, 1, 'Gracias por compartir tu experiencia, seguimos mejorando el sistema.', CURRENT_TIMESTAMP);


-- ========================
-- 💬 TABLA: FEEDBACK
-- ========================
INSERT INTO feedback (feedbackid, usuarioid, feedbacktipo, feedbackdescripcion, feedbackrating, feedbackfecha)
VALUES
    (1, 8, 'GENERAL', 'La aplicación EcoShield me parece intuitiva y fácil de usar. Excelente interfaz.', 5, CURRENT_TIMESTAMP),
    (2, 3, 'SUGGESTION', 'Sería genial que la app permita comparar plagas detectadas entre usuarios.', 4, CURRENT_TIMESTAMP),
    (3, 4, 'APP_PROBLEM', 'En algunos casos la detección tarda demasiado cuando la conexión es lenta.', 3, CURRENT_TIMESTAMP),
    (4, 8, 'GENERAL', 'EcoShield ha mejorado mi producción agrícola reduciendo el uso de pesticidas.', 5, CURRENT_TIMESTAMP),
    (5, 6, 'SUGGESTION', 'Podrían incluir recordatorios de fumigación o tratamiento automático.', 4, CURRENT_TIMESTAMP),
    (6, 7, 'APP_PROBLEM', 'No puedo subir fotos grandes, sería bueno un mensaje de error más claro.', 2, CURRENT_TIMESTAMP),
    (7, 9, 'GENERAL', 'Muy útil para identificar enfermedades del tomate. Resultados precisos.', 5, CURRENT_TIMESTAMP),
    (8, 10, 'SUGGESTION', 'Faltan más variedades de plagas para cultivos de maíz.', 4, CURRENT_TIMESTAMP),
    (9, 11, 'GENERAL', 'Excelente iniciativa ecológica, promueve la agricultura sostenible.', 5, CURRENT_TIMESTAMP),
    (10, 12, 'APP_PROBLEM', 'En iOS se cierra la app al abrir el historial de detecciones.', 2, CURRENT_TIMESTAMP),
    (11, 5, 'SUGGESTION', 'Podrían incluir consejos según el clima de mi región.', 4, CURRENT_TIMESTAMP),
    (12, 12, 'GENERAL', 'Desde el panel de administrador puedo gestionar todo fácilmente. Muy bien.', 5, CURRENT_TIMESTAMP);


-- ========================
-- 💬 TABLA: POST_LIKE
-- ========================
INSERT INTO post_like (postid, usuarioid, fechalike)
VALUES
    (1, 3, CURRENT_TIMESTAMP),
    (1, 8, CURRENT_TIMESTAMP),
    (1, 1, CURRENT_TIMESTAMP),
    (2, 4, CURRENT_TIMESTAMP),
    (2, 5, CURRENT_TIMESTAMP),
    (3, 2, CURRENT_TIMESTAMP),
    (4, 6, CURRENT_TIMESTAMP),
    (5, 10, CURRENT_TIMESTAMP),
    (5, 1, CURRENT_TIMESTAMP),
    (5, 9, CURRENT_TIMESTAMP);



-- ========================
-- 💬 TABLA: COMENTARIO_LIKE
-- ========================
INSERT INTO comentario_like (comentarioid, usuarioid, fechalike)
VALUES
    (1, 1, CURRENT_TIMESTAMP),
    (1, 8, CURRENT_TIMESTAMP),
    (2, 3, CURRENT_TIMESTAMP),
    (3, 1, CURRENT_TIMESTAMP),
    (4, 5, CURRENT_TIMESTAMP),
    (5, 12, CURRENT_TIMESTAMP),
    (6, 1, CURRENT_TIMESTAMP),
    (7, 2, CURRENT_TIMESTAMP),
    (8, 11, CURRENT_TIMESTAMP),
    (9, 8, CURRENT_TIMESTAMP),
    (10, 6, CURRENT_TIMESTAMP),
    (11, 10, CURRENT_TIMESTAMP),
    (12, 4, CURRENT_TIMESTAMP),
    (13, 9, CURRENT_TIMESTAMP),
    (14, 1, CURRENT_TIMESTAMP);




SELECT setval(pg_get_serial_sequence('rol', 'rolid'), COALESCE(MAX(rolid), 0) + 1, false) FROM rol;
SELECT setval(pg_get_serial_sequence('usuario', 'usuarioid'), COALESCE(MAX(usuarioid), 0) + 1, false) FROM usuario;
SELECT setval(pg_get_serial_sequence('plaga', 'plagaid'), COALESCE(MAX(plagaid), 0) + 1, false) FROM plaga;
SELECT setval(pg_get_serial_sequence('enfermedad', 'enfermedadid'), COALESCE(MAX(enfermedadid), 0) + 1, false) FROM enfermedad;
SELECT setval(pg_get_serial_sequence('blog', 'blogid'), COALESCE(MAX(blogid), 0) + 1, false) FROM blog;
SELECT setval(pg_get_serial_sequence('post', 'postid'), COALESCE(MAX(postid), 0) + 1, false) FROM post;
SELECT setval(pg_get_serial_sequence('comentario', 'comentarioid'), COALESCE(MAX(comentarioid), 0) + 1, false) FROM comentario;
SELECT setval(pg_get_serial_sequence('feedback', 'feedbackid'), COALESCE(MAX(feedbackid), 0) + 1, false) FROM feedback;
SELECT setval(pg_get_serial_sequence('post_like', 'likeid'), COALESCE(MAX(likeid), 0) + 1, false) FROM post_like;
SELECT setval(pg_get_serial_sequence('comentario_like', 'likeid'), COALESCE(MAX(likeid), 0) + 1,false) FROM comentario_like;
