package gs.calendar.appointments

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [CoreComponent::class])
internal interface ApplicationComponent {

    fun inject(application: Application)

}
