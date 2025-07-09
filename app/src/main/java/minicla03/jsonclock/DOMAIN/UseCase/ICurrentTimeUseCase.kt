package minicla03.jsonclock.DOMAIN.UseCase

import minicla03.jsonclock.DOMAIN.Model.DataTimeInfo

interface ICurrentTimeUseCase
{
    suspend fun getFestivita(): DataTimeInfo?
}