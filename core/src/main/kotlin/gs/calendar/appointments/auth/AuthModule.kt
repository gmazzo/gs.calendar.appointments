package gs.calendar.appointments.auth

import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
internal interface AuthModule {

    @Binds
    @Reusable
    fun bindAuthService(impl: AuthServiceImpl): AuthService

}
