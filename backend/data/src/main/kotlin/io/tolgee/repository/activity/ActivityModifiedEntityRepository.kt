package io.tolgee.repository.activity

import io.tolgee.activity.data.ActivityType
import io.tolgee.dtos.query_results.TranslationHistoryView
import io.tolgee.model.activity.ActivityModifiedEntity
import io.tolgee.model.activity.ActivityModifiedEntityId
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ActivityModifiedEntityRepository : JpaRepository<ActivityModifiedEntity, ActivityModifiedEntityId> {

  @Query(
    """
    select ame.modifications as modifications, ar.timestamp as timestamp,
    u.name as authorName, u.avatarHash as authorAvatarHash, u.username as authorEmail,
    u.id as authorId, u.deletedAt as authorDeletedAt, ame.revisionType as revisionType
    from ActivityModifiedEntity ame 
    join ame.activityRevision ar
    join UserAccount u on ar.authorId = u.id
    where ame.entityClass = 'Translation' and ame.entityId = :translationId
    and ar.type not in :ignoredActivityTypes
  """
  )
  fun getTranslationHistory(
    translationId: Long,
    pageable: Pageable,
    ignoredActivityTypes: List<ActivityType>
  ): Page<TranslationHistoryView>
}
