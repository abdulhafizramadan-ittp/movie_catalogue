## INSTRUMENTATION SCENARIO
Instrumentation testing ini menggunakan `junit 4.13.2`, `espresso 3.4.0` dan menggunakan `espresso-idling-resource 3.4.0`.

***

### Discover movies

- Memastikan `rv_movie` dalam keadaan tampil
- Gulir `rv_movie`  ke posisi data terakhir.


### Movie detail

- Memberi tindakan klik pada data pertama di  `rv_movie`.
- Memastikan `iv_movie_poster` dalam keadaan tampil.
- Memastikan `tv_movie_status` dalam keadaan tampil.
- Memastikan `tv_movie_runtime` dalam keadaan tampil.
- Memastikan `tv_movie_rating` dalam keadaan tampil.
- Memastikan `tv_movie_title` dalam keadaan tampil.
- Memastikan `tv_movie_synopsis` dalam keadaan tampil.


### Discover tv shows

- Memastikan bottom navigation dalam keadaan tampil
- Memberi tindakan klik pada bottom navagation `Tv Show`.
- Memastikan `rv_tv_show` dalam keadaan tampil.
- Gulir `rv_tv_movie`  ke posisi data terakhir.


### Tv detail

- Memastikan bottom navigation dalam keadaan tampil
- Memberi tindakan klik pada bottom navagation `Tv Show`.
- Memastikan `rv_tv_show` dalam keadaan tampil.
- Memberi tindakan klik pada data pertama di  `rv_tv_show`.
- Memastikan `iv_tv_show_poster` dalam keadaan tampil.
- Memastikan `tv_show_status` dalam keadaan tampil.
- Memastikan `tv_show_season` dalam keadaan tampil.
- Memastikan `tv_show_rating` dalam keadaan tampil.
- Memastikan `tv_show_title` dalam keadaan tampil.
- Memastikan `tv_show_synopsis` dalam keadaan tampil.

### Favorite Movies

- Memastikan bottom navigation dalam keadaan tampil
- Memberi tindakan klik pada bottom navagation `Favorite`.
- Memastikan `rv_favorite_list` dalam keadaan tampil.

### Favorite Tv Show

- Memastikan bottom navigation dalam keadaan tampil
- Memberi tindakan klik pada bottom navagation `Favorite`.
- Memastikan Tab layout dalam keadaan tampil
- Memberi tindakan klik pada Tab layout `Tv Show`
- Memastikan `rv_favorite_list` dalam keadaan tampil.
