from django.contrib.auth.models import AbstractUser
from django.db import models


# Create your models here.

class User(AbstractUser):

    class Type(models.IntegerChoices):
        ADMIN = 0
        STORE = 1
        END_USER = 2

    user_level = models.IntegerField(choices=Type.choices, default=Type.END_USER)
