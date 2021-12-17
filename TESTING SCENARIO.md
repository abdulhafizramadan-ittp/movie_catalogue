## UNIT TESTING SCENARIO
Unit testing ini menggunakan `junit 4.13.2` dan mengunakan `mockk 1.12.1` sebagai mocking class/object.

***

### MovieViewModelTest

#### Memuat discover movies

- Memastikan `listMovies` tidak `null`.
- Memastikan data `listMovies` sesuai dengan yang diharapkan.
- Melakukan `verifikasi` bahwa method `discoverMovies()` dipanggil.
- Melakukan `verifikasi` bahwa properti `listMovies` dipanggil.

#### Data kosong discover movies

- Memastikan `listMovies` tidak `null`.
- Memastikan jumlah data `listMovies` kosong/`empty` .
- Memastikan data `listMovies` sesuai  dengan yang diharapkan.
- Melakukan `verifikasi` bahwa method `discoverMovies()` dipanggil.
- Melakukan `verifikasi` bahwa properti `listMovies` dipanggil.

#### Gagal memuat discover movies

- Memastikan `listMovies` bernilai `null`.
- Memastikan `errorDiscoverMovies` tidak `null`.
- Memastikan `errorDiscoverMovies` bernilai `true`.
- Melakukan `verifikasi` bahwa method `discoverMovies()` dipanggil.
- Melakukan `verifikasi` bahwa properti `listMovies` dipanggil.
- Melakukan `verifikasi` bahwa properti `errorDiscoverMovies` dipanggil.


### TvShowViewModelTest

#### Memuat discover tv show

- Memastikan `listTvShows` tidak `null`.
- Memastikan data `listTvShows` sesuai dengan yang diharapkan.
- Melakukan `verifikasi` bahwa method `discoverTvShows()` dipanggil.
- Melakukan `verifikasi` bahwa properti `listTvShows` dipanggil.

#### Data kosong discover tv show

- Memastikan `listTvShows` tidak `null`.
- Memastikan data `listTvShows` kosong/`empty` .
- Memastikan data `listTvShows` sesuai  dengan yang diharapkan.
- Melakukan `verifikasi` bahwa method `discoverTvShows()` dipanggil.
- Melakukan `verifikasi` bahwa properti `listTvShows` dipanggil.

#### Gagal memuat discover tv show

- Memastikan `listTvShows` bernilai `null`.
- Memastikan `errorDiscoverMovies` tidak `null`.
- Memastikan `errorDiscoverMovies` bernilai `true`.
- Melakukan `verifikasi` bahwa method `discoverMovies()` dipanggil.
- Melakukan `verifikasi` bahwa properti `listTvShows` dipanggil.
- Melakukan `verifikasi` bahwa properti `errorDiscoverMovies` dipanggil.


### MovieDetailViewModelTest

#### Memuat movie detail

- Memastikan `movieDetail` tidak `null`.
- Memastikan data `movieDetail` sesuai dengan yang diharapkan.
- Memastikan data `movieDetail` tidak kosong.
- Melakukan `verifikasi` bahwa method `getMovieDetail(movieId)` dipanggil.
- Melakukan `verifikasi` bahwa properti `movieDetail` dipanggil.

#### Data kosong movie detail

- Memastikan `movieDetail` tidak `null`.
- Memastikan data `movieDetail` sesuai  dengan yang diharapkan.
- Memastikan data `movieDetail` kosong.
- Melakukan `verifikasi` bahwa method `getMovieDetail(movieId)` dipanggil.
- Melakukan `verifikasi` bahwa properti `movieDetail` dipanggil.

#### Gagal memuat movie detail

- Memastikan `movieDetail` bernilai `null`.
- Memastikan `movieDetailError` tidak `null`.
- Memastikan `movieDetailError` bernilai `true`.
- Melakukan `verifikasi` bahwa method `getMovieDetail(movieId)` dipanggil.
- Melakukan `verifikasi` bahwa properti `movieDetail` dipanggil.
- Melakukan `verifikasi` bahwa properti `movieDetailError` dipanggil.


### TvShowDetailViewModelTest

#### Memuat tv show detail

- Memastikan `tvShowDetail` tidak `null`.
- Memastikan data `tvShowDetail` sesuai dengan yang diharapkan.
- Memastikan data `tvShowDetail` tidak kosong.
- Melakukan `verifikasi` bahwa method `getTvShowDetail(tvShowId)` dipanggil.
- Melakukan `verifikasi` bahwa properti `tvShowDetail` dipanggil.

#### Data kosong tv show detail

- Memastikan `tvShowDetail` tidak `null`.
- Memastikan data `tvShowDetail` sesuai  dengan yang diharapkan.
- Memastikan data `tvShowDetail` kosong.
- Melakukan `verifikasi` bahwa method `getTvShowDetail(tvShowId)` dipanggil.
- Melakukan `verifikasi`  bahwa properti `tvShowDetail` dipanggil.

#### Gagal memuat movie detail

- Memastikan `tvShowDetail` bernilai `null`.
- Memastikan `tvShowDetailError` tidak `null`.
- Memastikan `tvShowDetailError` bernilai `true`.
- Melakukan `verifikasi` bahwa method `getTvShowDetail(movieId)` dipanggil.
- Melakukan `verifikasi` bahwa properti `tvShowDetail` dipanggil.
- Melakukan `verifikasi` bahwa properti `tvShowDetailError` dipanggil.


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
- Memastikan `tv_movie_tagline` dalam keadaan tampil.
- Memastikan `tv_movie_release` dalam keadaan tampil.
- Memastikan `tv_movie_language` dalam keadaan tampil.
- Memastikan `tv_movie_synopsis` dalam keadaan tampil.


### Discover tv shows

- Memastikan tab `Tv Show` dalam keadaan tampil.
- Memberi tindakan klik pada tab  `Tv Show`.
- Memastikan `rv_tv_show` dalam keadaan tampil.
- Gulir `rv_tv_movie`  ke posisi data terakhir.


### Tv detail

- Memastikan tab `Tv Show` dalam keadaan tampil.
- Memberi tindakan klik pada tab  `Tv Show`.
- Memberi tindakan klik pada data pertama di  `rv_tv_show`.
- Memastikan `iv_tv_show_poster` dalam keadaan tampil.
- Memastikan `tv_show_status` dalam keadaan tampil.
- Memastikan `tv_show_season` dalam keadaan tampil.
- Memastikan `tv_show_rating` dalam keadaan tampil.
- Memastikan `tv_show_title` dalam keadaan tampil.
- Memastikan `tv_show_tagline` dalam keadaan tampil.
- Memastikan `tv_show_release` dalam keadaan tampil.
- Memastikan `tv_show_language` dalam keadaan tampil.
- Memastikan `tv_show_synopsis` dalam keadaan tampil.