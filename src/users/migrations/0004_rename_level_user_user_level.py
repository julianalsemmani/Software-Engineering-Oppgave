# Generated by Django 3.2.8 on 2021-10-18 19:57

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('users', '0003_rename_user_level_user_level'),
    ]

    operations = [
        migrations.RenameField(
            model_name='user',
            old_name='level',
            new_name='user_level',
        ),
    ]
