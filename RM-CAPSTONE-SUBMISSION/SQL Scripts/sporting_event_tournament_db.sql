PGDMP                         {            sporting_event_tournament_db    15.2    15.2 
    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    24736    sporting_event_tournament_db    DATABASE     �   CREATE DATABASE sporting_event_tournament_db WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Filipino_Philippines.1252';
 ,   DROP DATABASE sporting_event_tournament_db;
                postgres    false                        2615    24737    tournament_schema    SCHEMA     !   CREATE SCHEMA tournament_schema;
    DROP SCHEMA tournament_schema;
                postgres    false            �            1259    24739 
   tournament    TABLE     �   CREATE TABLE tournament_schema.tournament (
    tournament_id bigint NOT NULL,
    isactive boolean,
    sports_category character varying(255),
    tournament_name character varying(255),
    tournament_style character varying(255)
);
 )   DROP TABLE tournament_schema.tournament;
       tournament_schema         heap    postgres    false    5            �            1259    24738    tournament_tournament_id_seq    SEQUENCE     �   ALTER TABLE tournament_schema.tournament ALTER COLUMN tournament_id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME tournament_schema.tournament_tournament_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            tournament_schema          postgres    false    5    215            �          0    24739 
   tournament 
   TABLE DATA           |   COPY tournament_schema.tournament (tournament_id, isactive, sports_category, tournament_name, tournament_style) FROM stdin;
    tournament_schema          postgres    false    215   7       �           0    0    tournament_tournament_id_seq    SEQUENCE SET     U   SELECT pg_catalog.setval('tournament_schema.tournament_tournament_id_seq', 2, true);
          tournament_schema          postgres    false    214            f           2606    24745    tournament tournament_pkey 
   CONSTRAINT     n   ALTER TABLE ONLY tournament_schema.tournament
    ADD CONSTRAINT tournament_pkey PRIMARY KEY (tournament_id);
 O   ALTER TABLE ONLY tournament_schema.tournament DROP CONSTRAINT tournament_pkey;
       tournament_schema            postgres    false    215            �   @   x�3�,�tU.�/*)��HU��+I-�K,���K��t���̈́��K*sR���j0¢#F��� Bm#(     