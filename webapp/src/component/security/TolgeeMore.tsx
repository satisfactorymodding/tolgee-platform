import { Box, Typography, Link } from '@mui/material';
import { useTranslate, T } from '@tolgee/react';

export function TolgeeMore() {
  const { t } = useTranslate();
  return (
    <Box mb={2}>
      <Typography color="textSecondary" variant="h5">
        {t('login_more_title')}
      </Typography>
      <Box mb={3} />
      <Typography color="textSecondary" variant="body2" fontSize={14}>
        <T
          keyName="login_tolgee_website_link"
          params={{
            link: <Link href="https://discord.gg/xkVJ73E" target="_blank" />,
          }}
        />
      </Typography>

      <Typography color="textSecondary" variant="body2" fontSize={14}>
        <T
          keyName="login_tolgee_documentation_link"
          params={{
            link: (
              <Link
                href="https://docs.ficsit.app/satisfactory-modding/latest/Development/Localization.html"
                target="_blank"
              />
            ),
          }}
        />
      </Typography>
    </Box>
  );
}
